	AREA	DisplayResult, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

	MOV R6, #0		; R6, operand memory
	MOV R7, #0xFFFFFFFF	; R7, operand memory
	MOV R8, #0xFFFFFFFF	; R8, operand memory

	MOV R4, #0		; First operator mem.
	MOV R11, #0		; Second operator mem.

	MOV R5, #0xFFFFFFFF	; Total
	LDR R10, = 10		; Loading 10 into R10 (for calculations)

read
	BL	getkey		; read key from console
	CMP	R0, #0x0D  	; while (key != CR)
	BEQ	endRead		; {
	BL	sendchar	; 	echo key back to console

	CMP R0, #'+'		; 	if (key == '+')
	BEQ opadd		; 		B opadd;
	CMP R0, #'-'   		;	 else if (key == '-')
	BEQ opsub		; 		B opsub;
	CMP R0, #'*'   		;	 else if (key == '*')
	BEQ opmul		;	 	B opmul;
				;	 else {
	MUL R6, R10, R6		; 		result = result * 10;
	SUB R0, R0, #0x30	;		value = char - 0x30; (converting from ASCII to numeric value)
	ADD R6, R0, R6		; 		result =  value + result;
				;	}
	B	read		; }

opadd
	CMP R4, #0		; if (opStorage1 == 0)
	BNE op2add		; {
	MOV R4, #1		; 	opStorage1 = 1; (addition)
	B readNext		; }
opsub
	CMP R4, #0		; if (opStorage1 == 0)
	BNE op2sub		; {
	MOV R4, #2		; 	opStorage1 = 2; (subtraction)
	B readNext		; }
opmul
	CMP R4, #0		; if (opStorage1 == 0)
	BNE op2mul		; {
	MOV R4, #3		; 	opStorage1 = 3; (multiplication)
	B readNext		; }

op2add
	MOV R11, #1		; opStorage2 = 1;
	B readNext		;

op2sub
	MOV R11, #2		; opStorage2 = 2;
	B readNext		;

op2mul
	MOV R11, #3		; opStorage2 = 3;
	B readNext		;

readNext			; R8 = op1 / R7 = op2 / R6 = op3 / R6 = tmp
	CMP R8, #0xFFFFFFFF	; if (op1 == 0)
	BNE elseif		; {
	MOV R8, R6 		;	op1 = tmp;
	MOV R6, #0		;	tmp = 0;
	B read
elseif				; }
	CMP R7, #0xFFFFFFFF	; else if (op2 == 0)
	BNE els			; {
	MOV R7, R6		;	op2 = tmp;
	MOV R6, #0		;	tmp = 0;
	B read			; }
els						; else {
	B read			; 	op3 = tmp;
				; }
endRead
	CMP R11, #0		; if (opStorage2 == 0) {
	BNE threeOprds		;	op2 = tmp;
	MOV R7, R6		; }

threeOprds
	CMP R4, #3		; if (opStorage1 == MUL)
	BNE oprt2mul		; {
	MUL R5, R8, R7		;	total = op1 * op2;
	CMP R11, #3		;	if (opStorage2 == MUL)
	BNE oprt2check		;	{
	MUL R5, R6, R5		;		total *= op3;
	B endcalc		;	}
						; }
oprt2mul			;
	CMP R11, #3		; if (opStorage2 == MUL) 
	BNE oprt1check		; {
	MUL R5,	R7, R6		;	total = op2 * op1;
	B oprt1check		; }

oprt2check
	CMP R11, #1		; if (opStorage2 == ADD)
	BNE checksub		; {
	ADD R5, R5, R6		;	total += op3;
	B endcalc		; }

checksub
	CMP R11, #2		; if (opStorage2 == SUB)
	BNE endcalc		; {
	SUB R5, R5, R6		;	total -= op3;
	B endcalc		; }

oprt1check
	CMP R4, #1		; if (opStorage1 == ADD)
	BNE checksub2		; {
	CMP R5, #0xFFFFFFFF	;	if (totalIsUsed)
	BEQ totalNotUsed	;	{
	ADD R5, R5, R8		;	total += op1;
	B endcalc		;	}
totalNotUsed			;	else {
	ADD R5, R8, R7		;		total = op1 + op2;
	B oprt2check		;	}
				; }
checksub2
	CMP R4, #2		; if (opStorage1 == SUB)
	BNE endcalc		; {
	CMP R5, #0xFFFFFFFF	; 	if (totalIsUsed) 
	BEQ totalUnused		;	{
	SUB R5, R8, R5		;		total -= op3;
	B endcalc		;	}
	totalUnused		;	else {
	SUB R5, R8, R7		;		total = op1 - op2;
	B oprt2check		;	}
				; }
endcalc

	MOV R11, #0		; quotient = 0;
	MOV R0, #'='
	BL sendchar		; sendchar '=' to the display

	MOV R9, #10		; R9 = 10

while				; while (remainder > 9)
						; {
	MOV R6, R5		; 	remainder = total;
	CMP R6, #10		;	if (remainder >= 10)
	BLT printlast		;	{

divide
	CMP R6, R10		; 		while (remainder > powerOfTen)
	BLT enddiv		; 		{
	ADD R11, R11, #1	; 			quotient = quotient + 1;
	SUB R6, R6, R10		;			remainder = remainder - powerOfTen
	B divide		;		}
enddiv
	CMP R11, #9		; 		if (quotient > 9)
	BLE print		; 		{
	MUL R10, R9, R10	;			powerOfTen = powerOfTen * 10;
	MOV R11, #0		;			quotient = 0;
	B while			; 		}
				;		else 
print				;		{
	ADD R0, R11, #0x30	; 			output = quotient + 0x30; (Convert to ASCII value)
	BL sendchar		;			printkey();
	MOV R10, #10		; 			powerOfTen = 10;
	MOV R5, R6		;			total = remainder;
	CMP R6, #10		;			if (remainder >= 10)
	BLT printlast		;			{
	MOV R11, #0		;				quotient = 0;
	B while			; 			}
				; 		}
				; 	}
printlast			; }
	ADD R6, R6, #0x30	; remainder += 0x30;
	MOV R0, R6		; output = remainder;
	BL sendchar		; printkey();

stop	B	stop

	END