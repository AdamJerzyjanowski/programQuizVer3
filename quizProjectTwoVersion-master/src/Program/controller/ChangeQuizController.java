package Program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class ChangeQuizController {

    public static String path;
    @FXML
    private Button btnClose;

    @FXML
    private Button Load;

    @FXML
    private ComboBox<?> comBoxChoiceQuiz;

    @FXML
    public void initialize() {
        comBoxChoiceQuiz.getItems().addAll(getNameFile());
        comBoxChoiceQuiz.setPromptText((String) getNameFile().get(0));
    }

    @FXML
    void OnActionClose(ActionEvent event) {
        System.exit(3);
    }

    @FXML
    void OnActionLoad(ActionEvent event) throws Exception  {
        path =  "src/Program/pytania"+comBoxChoiceQuiz.getValue()+".txt";
        ControllerQuiz quiz = new ControllerQuiz();


        if(comBoxChoiceQuiz.getValue()==null){
            quiz.file = "src/Program/pytania/" + getNameFile().get(0) + ".txt";
        }else {
            quiz.file = "src/Program/pytania/" + comBoxChoiceQuiz.getValue() + ".txt";

        }

        try {
            quiz.start();
        }catch (Exception e){
        }

        Stage stage = (Stage) Load.getScene().getWindow();
        stage.close();
    }

    public void start() throws Exception {
        Stage primaryStage = new Stage();
        Parent parViewQestion = FXMLLoader.load(getClass().getResource("../view/viewChangeQuiz.fxml"));
        Scene viewQestion = new Scene(parViewQestion);
        viewQestion.getStylesheets().add("Program/view/changeQuizStyleView");
        primaryStage.setTitle("Wybierz Quiz");
        primaryStage.setScene(viewQestion);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public static ArrayList getNameFile() {
        File qestion = new File("src/Program/pytania");
        String[] listNameFile = qestion.list();
        ArrayList listNameFileArry = new ArrayList();
        for (int i = 0; i < listNameFile.length; i++) {
            String onlyNameNotWithTxt = listNameFile[i].substring(0, listNameFile[i].length() - 4);
            String actualValue = listNameFile[i].substring(listNameFile[i].length() - 4, listNameFile[i].length());
            String txt = ".txt";
            if(txt.equals(actualValue)) {
                listNameFileArry.add(onlyNameNotWithTxt);
            }
        }
        return listNameFileArry;
    }


}

