package AssessmentSystem.results;

import AssessmentFeed.AssessmentFeed;

public class AIProject extends Assessment {
    public static final double FUNCTIONALITY_SHARE = 0.4;
    public static final double VERIFICATION_SHARE = 0.6;

    private final double functionalityMark;
    private final double verificationMark;

    public AIProject(String cid, double weighting) {
        super(AssessmentFeed.AI_PROJECT, true, weighting);
        functionalityMark = AssessmentFeed.getResult(cid, AssessmentFeed.AI_PROJECT,
                AssessmentFeed.FUNCTIONALITY_MARK);
        verificationMark = AssessmentFeed.getResult(cid, AssessmentFeed.AI_PROJECT,
                AssessmentFeed.VERIFICATION_MARK);
    }

    @Override
    public double getPercentage() {
        return FUNCTIONALITY_SHARE * functionalityMark + VERIFICATION_SHARE * verificationMark;
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.printf("  %s: %.2f%n", AssessmentFeed.FUNCTIONALITY_MARK, functionalityMark);
        System.out.printf("  %s: %.2f%n", AssessmentFeed.VERIFICATION_MARK, verificationMark);
        System.out.printf("  Final mark: %.2f%%%n", getPercentage());
    }
}
