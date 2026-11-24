package AssessmentSystem.results;

import java.util.ArrayList;

public abstract class Student implements Reportable {
    public static final double AI_DISCREPANCY_THRESHOLD = 20.0;
    private static final double TOLERANCE = 1e-9;

    private final String cid;
    private final String name;
    private final String type;
    protected ArrayList<Assessment> assessments = new ArrayList<Assessment>();

    public Student(String cid, String name, String type) {
        this.cid = cid;
        this.name = name;
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Assessment> getAssessments() {
        return new ArrayList<Assessment>(assessments);
    }

    public Assessment getAssessment(String title) {
        for (Assessment a : assessments) {
            if (a.getTitle().equals(title)) return a;
        }
        return null;
    }

    public double getModuleMark() {
        double total = 0;
        double totalWeight = 0;
        for (Assessment a : assessments) {
            total += a.getWeighting() * a.getPercentage();
            totalWeight += a.getWeighting();
        }
        if (totalWeight == 0) return 0;
        return total / totalWeight;
    }

    public double getAverage(boolean aiPermitted) {
        double total = 0;
        int count = 0;
        for (Assessment a : assessments) {
            if (a.isAiPermitted() == aiPermitted) {
                total += a.getPercentage();
                count++;
            }
        }
        if (count == 0) return -1;
        return total / count;
    }

    public boolean hasAiDiscrepancy() {
        double aiAverage = getAverage(true);
        double nonAiAverage = getAverage(false);
        if (aiAverage < 0 || nonAiAverage < 0) return false;
        return aiAverage - nonAiAverage >= AI_DISCREPANCY_THRESHOLD - TOLERANCE;
    }

    @Override
    public void printReport() {
        System.out.println("");
        System.out.printf("%s (CID: %s, Student type: %s)%n", getName(), cid, type);
        for (Assessment a : assessments) {
            a.printReport();
        }
        String hasAi;
        if (hasAiDiscrepancy()) {
            hasAi = "Yes";
        } else{
            hasAi="No";
        }
        System.out.println("");
        System.out.println("--------------------------------------------------");
        System.out.printf("SUMMARY for %s:%n", name);
        System.out.printf("Overall weighted module mark: %.2f%%%n", getModuleMark());
        System.out.printf("AI-permitted average: %.2f%%, non-AI average: %.2f%%%n", getAverage(true), getAverage(false));
        System.out.printf("AI discrepancy condition met: %s%n", hasAi);
        System.out.println("--------------------------------------------------");
    }
}
