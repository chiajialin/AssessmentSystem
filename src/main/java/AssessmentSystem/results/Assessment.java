package AssessmentSystem.results;

public abstract class Assessment implements Reportable {
    public static final double MAX_MARK = 100.0;

    private final String title;
    private final double maxMark;
    private final boolean aiPermitted;
    private final double weighting;

    public Assessment(String title, boolean aiPermitted, double weighting) {
        this.title = title;
        this.maxMark = MAX_MARK;
        this.aiPermitted = aiPermitted;
        this.weighting = weighting;
    }

    public String getTitle() {
        return title;
    }

    public double getMaxMark() {
        return maxMark;
    }

    public boolean isAiPermitted() {
        return aiPermitted;
    }

    public double getWeighting() {
        return weighting;
    }

    public abstract double getPercentage();

    @Override
    public void printReport() {
        String ai;
        if (aiPermitted){
            ai = "AI permitted";
        } else {
            ai = "AI not permitted";
        }
        System.out.printf("Assessment type: %s (%s)%n",  title, ai);
    }
}
