package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class HarnessRecords {
    private static final int MAKE_INDEX = 0;
    private static final int MODEL_NUMB_INDEX = 1;
    private static final int TIMES_USED_INDEX = 2;
    private static final int CHECKED_BY_INDEX = 3;
    private static final int ON_LOAN_INDEX = 4;
    private static final int ON_LOAN_TO_MEMBER_INDEX = 5;

    private ArrayList<Harness> harnessRecords;

    HarnessRecords() {
        this.harnessRecords = null;
    }

    HarnessRecords(BufferedReader bufferedReader) throws IOException {
        this.harnessRecords = new ArrayList<>();

        String harnessNumberString = bufferedReader.readLine();
        int numberOfHarnesses = Integer.parseInt(harnessNumberString);

        for (int count = 0; count < numberOfHarnesses; count++) {
            String harnessLine = bufferedReader.readLine();
            Harness harness = null;
            if (harnessLine != null) {
                String[] harnessInfo = harnessLine.split(", |,");
                int timesUsed = Integer.parseInt(harnessInfo[TIMES_USED_INDEX]);
                if (timesUsed == 0) {
                    harness = new Harness(harnessInfo[MAKE_INDEX],
                            Integer.parseInt(harnessInfo[MODEL_NUMB_INDEX]),
                            harnessInfo[CHECKED_BY_INDEX]);
                } else if (timesUsed > 0) {
                    boolean onLoan = Boolean.parseBoolean(harnessInfo[ON_LOAN_INDEX]);
                    harness = new Harness(harnessInfo[MAKE_INDEX],
                            Integer.parseInt(harnessInfo[MODEL_NUMB_INDEX]),
                            Integer.parseInt(harnessInfo[TIMES_USED_INDEX]),
                            harnessInfo[CHECKED_BY_INDEX],
                            Boolean.parseBoolean(harnessInfo[ON_LOAN_INDEX]),
                            (onLoan) ? harnessInfo[ON_LOAN_TO_MEMBER_INDEX] : null);
                }
            }
            if (harness != null) {
                addHarness(harness);
            }
        }
        bufferedReader.close();
    }

    private boolean isEmpty() {
        return (this.harnessRecords.size() == 0);
    }

    public void addHarness(Harness harness) {
        this.harnessRecords.add(harness);
}

    private Harness findHarness(String make, int modelNumber) {
        boolean harnessFound = false;
        int index = 0;
        Harness harness = null;

        while (!harnessFound && index < this.harnessRecords.size() && !this.isEmpty()) {
            harness = this.harnessRecords.get(index);
            if (harness.make.equals(make) && harness.modelNumber == modelNumber) {
                harness = harnessRecords.get(index);
                harnessFound = true;
            }
            index++;
        }
        if (!harnessFound) {
            harness = null;
        }
        return harness;
    }

    public Harness checkHarness(String make, int modelNumber, String nameOfInstructor) {
        Harness harness = findHarness(make, modelNumber);
        if (harness != null && !harness.isHarnessOnLoan()) {
            harness.checkHarness(nameOfInstructor);
        } else {
            harness = null;
        }
        return harness;
    }

    public Harness loanHarness(String nameOfClubMember) {
        Harness harness = null;
        boolean harnessFound = false;
        int index = 0;

        while (!harnessFound && index < this.harnessRecords.size()) {
            harness = this.harnessRecords.get(index);
            if (!harness.isHarnessOnLoan()) {
                harness = harnessRecords.get(index);
                harnessFound = true;
            }
            index++;
        }
        if (!harnessFound) {
            harness = null;
        } else {
            harness.loanHarness(nameOfClubMember);
        }
        return harness;
    }

    public Harness returnHarness(String make, int modelNumber) {
        Harness harness = findHarness(make, modelNumber);
        if (harness != null) {
            harness.returnHarness();
        }
        return harness;
    }

    public Harness removeHarness(String make, int modelNumber) {
        Harness harness = findHarness(make, modelNumber);
        if (harness != null) {
            this.harnessRecords.remove(harness);
        }
        return harness;
    }
}