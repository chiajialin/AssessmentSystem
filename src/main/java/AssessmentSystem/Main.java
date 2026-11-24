package AssessmentSystem;

import AssessmentSystem.gui.ResultsWindow;
import AssessmentSystem.results.Student;
import AssessmentSystem.results.StudentLoader;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = StudentLoader.loadAll();

        System.out.println("TITLE: MODULE ASSESSMENT RESULTS");
        for (Student s : students) {
            s.printReport();
        }

        ResultsWindow window = new ResultsWindow(students);
        window.show();
    }
}
