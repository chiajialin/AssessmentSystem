package AssessmentSystem.gui;

import AssessmentSystem.results.Assessment;
import AssessmentSystem.results.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ResultsWindow {
    private final JFrame frame;

    public ResultsWindow(ArrayList<Student> students) {
        frame = new JFrame("Module results");
        frame.setSize(1200, 100 + 60 * students.size());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        ArrayList<String> titles = collectTitles(students);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(students.size() + 1, 1));
        panel.add(makeHeaderRow(students, titles));
        for (Student s : students) {
            panel.add(new StudentRowPanel(s, titles));
        }
        frame.getContentPane().add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    private ArrayList<String> collectTitles(ArrayList<Student> students) {
        ArrayList<String> titles = new ArrayList<String>();
        for (Student s : students) {
            for (Assessment a : s.getAssessments()) {
                if (!titles.contains(a.getTitle())) titles.add(a.getTitle());
            }
        }
        return titles;
    }

    private JPanel makeHeaderRow(ArrayList<Student> students, ArrayList<String> titles) {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1, titles.size() + 4));
        header.add(new JLabel("<html><b>Name</b></html>"));
        header.add(new JLabel("<html><b>CID</b></html>"));
        header.add(new JLabel("<html><b>Type</b></html>"));
        for (String title : titles) {
            String ai;
            if (isAiPermitted(students, title)){
                ai = "AI permitted";
            } else{
                ai = "No AI permitted";
            }
            header.add(new JLabel("<html><b>" + title + "</b><br>(" + ai + ")</html>"));
        }
        header.add(new JLabel("<html><b>Module mark</b><br>(weighted)</html>"));
        return header;
    }

    private boolean isAiPermitted(ArrayList<Student> students, String title) {
        for (Student s : students) {
            Assessment a = s.getAssessment(title);
            if (a != null) return a.isAiPermitted();
        }
        return false;
    }
}
