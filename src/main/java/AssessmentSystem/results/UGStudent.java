package AssessmentSystem.results;

public class UGStudent extends Student {
    public static final String TYPE = "UG";
    public static final double PROGRAMMING_TEST_WEIGHT = 0.30;
    public static final double AI_PROJECT_WEIGHT = 0.70;

    public UGStudent(String cid, String name) {
        super(cid, name, TYPE);
        assessments.add(new ProgrammingTest(cid, PROGRAMMING_TEST_WEIGHT));
        assessments.add(new AIProject(cid, AI_PROJECT_WEIGHT));
    }
}
