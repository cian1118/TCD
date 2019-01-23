package Week8;

public class Harness {
    final String make;
    final int modelNumber;
    private int timesUsed;
    private String lastCheckedBy;
    private boolean isOnLoan;
    private String nameOfMemberOnLoanTo;
    private static final int MAX_USES = 25;

    Harness(String make, int modelNumber, int timesUsed, String lastCheckedBy, boolean isOnLoan, String nameOfMemberOnLoanTo) {
        this.make = make;
        this.modelNumber = modelNumber;
        this.timesUsed = timesUsed;
        this.lastCheckedBy = lastCheckedBy;
        this.isOnLoan = isOnLoan;
        this.nameOfMemberOnLoanTo = nameOfMemberOnLoanTo;
    }

    Harness(String make, int modelNumber, String lastCheckedBy) {
        this.make = make;
        this.modelNumber = modelNumber;
        this.timesUsed = 0;
        this.lastCheckedBy = lastCheckedBy;
        this.isOnLoan = false;
        this.nameOfMemberOnLoanTo = null;
    }

    public void checkHarness(String nameOfInstructor) {
        if (!isHarnessOnLoan()) {
            this.timesUsed = 0;
            this.lastCheckedBy = nameOfInstructor;
        }
    }

    public boolean isHarnessOnLoan() {
        return (this.isOnLoan);
    }

    private boolean canHarnessBeLoaned() {
        return (this.timesUsed < MAX_USES && !isHarnessOnLoan());
    }

    public void loanHarness(String clubMember) {
        if (canHarnessBeLoaned()) {
            this.isOnLoan = true;
            this.nameOfMemberOnLoanTo = clubMember;
            this.timesUsed++;
        }
    }

    public void returnHarness() {
        if (isHarnessOnLoan()) {
            this.isOnLoan = false;
            this.nameOfMemberOnLoanTo = null;
        }
    }

    @Override
    public String toString() {
        return "Harness{" +
                "make='" + make + '\'' +
                ", modelNumber=" + modelNumber +
                ", timesUsed=" + timesUsed +
                ", lastCheckedBy='" + lastCheckedBy + '\'' +
                ", isOnLoan=" + isOnLoan +
                ", nameOfMemberOnLoanTo='" + nameOfMemberOnLoanTo + '\'' +
                '}';
    }
}