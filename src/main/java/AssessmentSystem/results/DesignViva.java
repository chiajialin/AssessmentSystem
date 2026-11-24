package AssessmentSystem.results;

import AssessmentFeed.AssessmentFeed;

public class DesignViva extends Assessment {
    public static final double DESIGN_SHARE = 0.5;
    public static final double EXPLANATION_SHARE = 0.5;

    private final double designMark;
    private final double explanationMark;

    public DesignViva(String cid, double weighting) {
        super(AssessmentFeed.DESIGN_VIVA, false, weighting);
        designMark = AssessmentFeed.getResult(cid, AssessmentFeed.DESIGN_VIVA,
                AssessmentFeed.DESIGN_MARK);
        explanationMark = AssessmentFeed.getResult(cid, AssessmentFeed.DESIGN_VIVA,
                AssessmentFeed.EXPLANATION_MARK);
    }

    @Override
    public double getPercentage() {
        return DESIGN_SHARE * designMark + EXPLANATION_SHARE * explanationMark;
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.printf("  %s: %.2f%n", AssessmentFeed.DESIGN_MARK, designMark);
        System.out.printf("  %s: %.2f%n", AssessmentFeed.EXPLANATION_MARK, explanationMark);
        System.out.printf("  Final mark: %.2f%%%n", getPercentage());
    }
}
