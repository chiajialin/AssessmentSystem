package AssessmentSystem.results;

import AssessmentFeed.AssessmentFeed;

public class ProgrammingTest extends Assessment {
    public static final double MAX_MARKS_FOR_NOT_EXPLAINED = 40.0;

    private final double programMark;
    private final boolean explained;

    public ProgrammingTest(String cid, double weighting) {
        super(AssessmentFeed.PROGRAMMING_TEST, false, weighting);
        programMark = AssessmentFeed.getResult(cid, AssessmentFeed.PROGRAMMING_TEST,
                AssessmentFeed.PROGRAM_MARK);
        explained = AssessmentFeed.getResult(cid, AssessmentFeed.PROGRAMMING_TEST,
                AssessmentFeed.EXPLAINED_SUCCESSFULLY) == 1.0;
    }

    @Override
    public double getPercentage() {
        if (explained) return programMark;
        return Math.min(programMark, MAX_MARKS_FOR_NOT_EXPLAINED);
    }

    @Override
    public void printReport() {
        super.printReport();
        String explainedText;
        if (explained) {
            explainedText = "Yes";
        } else {
            explainedText = "No (marks capped at 40%)";
        }
        System.out.printf("  %s: %.2f%n", AssessmentFeed.PROGRAM_MARK, programMark);
        System.out.printf("  %s: %s%n", AssessmentFeed.EXPLAINED_SUCCESSFULLY, explainedText);
        System.out.printf("  Final mark: %.2f%%%n", getPercentage());
    }
}
