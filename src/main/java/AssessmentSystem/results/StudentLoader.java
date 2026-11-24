package AssessmentSystem.results;

import java.util.ArrayList;

import AssessmentFeed.AssessmentFeed;

public class StudentLoader {

    public static ArrayList<Student> loadAll() {
        ArrayList<Student> students = new ArrayList<Student>();
        for (String cid : AssessmentFeed.getCIDs()) {
            try {
                students.add(load(cid));
            } catch (UnknownStudentTypeException e) {
                System.out.println("Skipping CID " + cid + ": " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Skipping CID " + cid + ", feed data is missing: " + e.getMessage());
            }
        }
        return students;
    }

    private static Student load(String cid) throws UnknownStudentTypeException {
        String name = AssessmentFeed.getStudentName(cid);
        String type = AssessmentFeed.getStudentType(cid);
        if (type.equals(UGStudent.TYPE)) return new UGStudent(cid, name);
        if (type.equals(MScStudent.TYPE)) return new MScStudent(cid, name);
        throw new UnknownStudentTypeException("unknown student type '" + type + "'");
    }
}
