package AssessmentSystem.results;

public class MScStudent extends Student {
    public static final String TYPE = "MSc";
    public static final double PROGRAMMING_TEST_WEIGHT = 0.25;
    public static final double AI_PROJECT_WEIGHT = 0.45;
    public static final double DESIGN_VIVA_WEIGHT = 0.30;

    public MScStudent(String cid, String name) {
        super(cid, name, TYPE);
        assessments.add(new ProgrammingTest(cid, PROGRAMMING_TEST_WEIGHT));
        assessments.add(new AIProject(cid, AI_PROJECT_WEIGHT));
        assessments.add(new DesignViva(cid, DESIGN_VIVA_WEIGHT));
    }
}
