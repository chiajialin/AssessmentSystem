package AssessmentSystem.gui;

import AssessmentSystem.results.Assessment;
import AssessmentSystem.results.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * One row of the results grid for one student.
 * The row is its own JPanel, so the whole row turns red when the AI
 * discrepancy condition is met.
 */
public class StudentRowPanel extends JPanel {

    public StudentRowPanel(Student student, ArrayList<String> assessmentTitles) {
        // Name, CID and type, one column per assessment, then the module mark.
        setLayout(new GridLayout(1, assessmentTitles.size() + 4));

        add(new JLabel(student.getName()));
        add(new JLabel(student.getCid()));
        add(new JLabel(student.getType()));

        for (String title : assessmentTitles) {
            Assessment a = student.getAssessment(title);
            if (a == null) {
                add(new JLabel("N/A"));
            } else {
                add(new JLabel(String.format("%.2f%%", a.getPercentage())));
            }
        }

        add(new JLabel(String.format("%.2f%%", student.getModuleMark())));

        // JLabels are see-through by default, so the panel colour shows behind them.
        if (student.hasAiDiscrepancy()) {
            setBackground(Color.RED);
        }
    }
}
