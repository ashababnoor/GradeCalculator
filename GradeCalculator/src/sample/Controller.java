package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class Controller {

    @FXML
    public TextField quizField0, quizField1, quizField2, quizField3, quizField4, quizField5;
    @FXML
    private TextField attendanceField, assignmentField, midField, finalField;
    @FXML
    private Button calculateButton;
    @FXML
    private Label gradeLabel;

    public TextField[] quizFields;

    public void initializeArray(){
        quizFields = new TextField[]{quizField0, quizField1, quizField2, quizField3, quizField4, quizField5};
    }

    public void calculateResult(ActionEvent actionEvent) {
        initializeArray();

        double totalMarks = 0.0;
        double[] quizMarks = new double[6];

        for (int i=0; i<6; i++){
            String s = quizFields[i].getText();
            try {
                quizMarks[i] = Double.parseDouble(s);
            }catch (NumberFormatException e){
                quizMarks[i] = 0.0;
            }
            if (quizMarks[i] > 20)
                quizMarks[i] = 20;
        }
        Arrays.sort(quizMarks);
        for (int i=5; i>=3; i--){
            totalMarks += quizMarks[i];
        }
        totalMarks = (totalMarks/60)*40;

        double attendanceMarks, assignmentMarks, midMarks, finalMarks;
        String s1 = attendanceField.getText();
        String s2 = assignmentField.getText();
        String s3 = midField.getText();
        String s4 = finalField.getText();

        try {
            attendanceMarks = Double.parseDouble(s1);
            assignmentMarks = Double.parseDouble(s2);
            midMarks = Double.parseDouble(s3);
            finalMarks = Double.parseDouble(s4);
        }catch (NumberFormatException e){
            return;
        }
        if (attendanceMarks > 5) attendanceMarks = 5;
        if (assignmentMarks > 10) assignmentMarks = 10;
        if (midMarks > 20) midMarks = 20;
        if (finalMarks > 25) finalMarks = 25;

        totalMarks += attendanceMarks + assignmentMarks + midMarks + finalMarks;

        String grade = "F | Grade Points: 0.00";
        if (totalMarks >= 55) grade = "D | Grade Points: 1.00";
        if (totalMarks >= 58) grade = "D+ | Grade Points: 1.33";
        if (totalMarks >= 62) grade = "C- | Grade Points: 1.67";
        if (totalMarks >= 66) grade = "C | Grade Points: 2.00";
        if (totalMarks >= 70) grade = "C+ | Grade Points: 2.33";
        if (totalMarks >= 74) grade = "B- | Grade Points: 2.67";
        if (totalMarks >= 78) grade = "B | Grade Points: 3.00";
        if (totalMarks >= 82) grade = "B+ | Grade Points: 3.33";
        if (totalMarks >= 86) grade = "A- | Grade Points: 3.67";
        if (totalMarks >= 90) grade = "A | Grade Points: 4.00";

        gradeLabel.setText("Grade: "+grade+" | Marks: "+String.format("%.2f", totalMarks));
    }
}
