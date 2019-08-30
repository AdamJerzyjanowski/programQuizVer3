package Program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.util.ArrayList;


public class ChangeQuizController {
    final static private String RELATIVEPATH = "src/Program/pytania/";
    final static private String FILEEXTENSION = ".txt";
    public static String path;
    @FXML
    private Button btnClose;

    @FXML
    private Button Load;

    @FXML
    private ComboBox<?> comboBoxChoiceQuiz;

    @FXML
    public void initialize() {
        comboBoxChoiceQuiz.getItems().addAll(getFilenames());
        comboBoxChoiceQuiz.setPromptText((String) getFilenames().get(0));
    }

    @FXML
    void OnActionClose(ActionEvent event) {
        System.exit(3);
    }

    @FXML
    void OnActionLoad(ActionEvent event) throws Exception {
        path = RELATIVEPATH + comboBoxChoiceQuiz.getValue() + FILEEXTENSION;
        QuizController quiz = new QuizController();
        if (comboBoxChoiceQuiz.getValue() == null) {
            quiz.FILE = RELATIVEPATH + getFilenames().get(0) + FILEEXTENSION;
        } else {
            quiz.FILE = RELATIVEPATH + comboBoxChoiceQuiz.getValue() + FILEEXTENSION;
        }

        try {
            quiz.start();
        } catch (Exception e) {
            Window owner = Load.getScene().getWindow();
            AlertController.showAlert(Alert.AlertType.INFORMATION, owner , "Błąd metody quiz.start ",
                    "z nie znanaych powodów metoda start nie działa");
        }
        Stage stage = (Stage) Load.getScene().getWindow();
        stage.close();
    }
    public void start() throws Exception {
        Stage primaryStage = new Stage();
        Parent parViewQuestion = FXMLLoader.load(getClass().getResource("../view/viewChangeQuiz.fxml"));
        Scene viewQuestion = new Scene(parViewQuestion);
        viewQuestion.getStylesheets().add("Program/view/changeQuizStyleView");
        primaryStage.setTitle("Wybierz Quiz");
        primaryStage.setScene(viewQuestion);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }
    public static ArrayList getFilenames() {
        File question = new File(RELATIVEPATH.substring(0,RELATIVEPATH.length()-1));
        String[] listNamesFile = question.list();
        ArrayList listNamesFileArry = new ArrayList();
        for (int i = 0; i < listNamesFile.length; i++) {
            String onlyNameNotWithTxt = listNamesFile[i].substring(0, listNamesFile[i].length() - 4);
            String actualValue = listNamesFile[i].substring(listNamesFile[i].length() - 4, listNamesFile[i].length());
            if (FILEEXTENSION.equals(actualValue)) {
                listNamesFileArry.add(onlyNameNotWithTxt);
            }
        }
        return listNamesFileArry;
    }
}

