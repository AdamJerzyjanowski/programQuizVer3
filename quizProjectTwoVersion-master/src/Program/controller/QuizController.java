package Program.controller;


import Program.model.ModelQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizController {

    @FXML
    private Label lblQuizTitle;

    @FXML
    private Label lblQuestion;
/*
    @FXML
    private Label lblAnwserA;

    @FXML
    private Label lblAnwserB;

    @FXML
    private Label lblAnwserD;

    @FXML
    private Label lblAnwserC;
*/

    @FXML
    private CheckBox checkBoxA;

    @FXML
    private CheckBox checkBoxC;

    @FXML
    private CheckBox checkBoxB;

    @FXML
    private CheckBox checkBoxD;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnNextQuestion;

    @FXML
    private Label lblQuestionIsFrom;

    @FXML
    private Label lblCorrectAnswers;

    @FXML
    private Label lblPosition;

    @FXML
    private Text txtAnswerC;

    @FXML
    private Text txtAnswerD;

    @FXML
    private Text txtAnswerA;

    @FXML
    private Text txtAnswerB;


    volatile int licznik = 0;
    volatile int positon = 0;
    List<ModelQuestion> questions = new ArrayList<>();
    public String testName;
    public int points;
    public static String FILE = "src/Program/pytania/pytania.txt";

    @FXML
    public void initialize() {
        if (licznik == 0) {

            /*OLD CODE
            lblQuestionIsFrom.setText("1/20");
            lblQuestion.setText(FileEqualQuestionController.getQuestionFromFile(FILE,licznik));
            lblAnwserA.setText(FileEqualQuestionController.getGoodAnswerFromFile(FILE,licznik));
            lblAnwserB.setText(FileEqualQuestionController.getBadAnswer1(FILE,licznik));
            lblAnwserC.setText(FileEqualQuestionController.getBadAnswer2(FILE,licznik));
            lblAnwserD.setText(FileEqualQuestionController.getBadAnswer3(FILE, licznik));
            */


            for (int i = 0; i < (FileEqualQuestionController.getHowMannyLineIsInFile(FILE) / 6); i++) {

                ModelQuestion tmpQuestion = new ModelQuestion(FileEqualQuestionController.getQuestionFromFile(FILE, licznik),
                        FileEqualQuestionController.getGoodAnswerFromFile(FILE, licznik), FileEqualQuestionController.getBadAnswer1(FILE, licznik),
                        FileEqualQuestionController.getBadAnswer2(FILE, licznik), FileEqualQuestionController.getBadAnswer3(FILE, licznik));
                questions.add(tmpQuestion);
                licznik++;
            }
            testName = getOnlyNameFile(FILE);
            Collections.shuffle(questions);// zmienianie kolejności pytań
            lblQuestion.setText(questions.get(positon).getQuestion()); // wyswietla pytanie
            lblQuestionIsFrom.setText("pytanie " + (positon + 1) + "/" + questions.size()); //wysiwetla które to pytanie z ilu
            txtAnswerA.setText(questions.get(positon).getRandomQestion().get(0));
            txtAnswerB.setText(questions.get(positon).getRandomQestion().get(1));
            txtAnswerC.setText(questions.get(positon).getRandomQestion().get(2));
            txtAnswerD.setText(questions.get(positon).getRandomQestion().get(3));
            positon++;
        }

    }

    @FXML
    void onCheckQestion(ActionEvent event) {
        if (ScoreController.checkCorrectAnswers(
                checkBoxA.isSelected(),
                checkBoxB.isSelected(),
                checkBoxC.isSelected(),
                checkBoxD.isSelected(),
                txtAnswerA.getText(),
                txtAnswerB.getText(),
                txtAnswerC.getText(),
                txtAnswerD.getText(),
                questions.get(positon - 1).getCorrectAnswer())) {
            points = Integer.parseInt(lblCorrectAnswers.getText()) + 1;
            //test
            //System.out.println(socker);
            lblCorrectAnswers.setText("" + points);

        }
        if ((positon) / questions.size() == 1) {
           // Window owner = btnCheck.getScene().getWindow();
            // AlertController.showAlert(Alert.AlertType.INFORMATION, owner , "Quiz ukończony",
            //         "Ukończyłeś "+ lblQuizTitle.getText() +"  zdobyłeś punktów "+lblCorrectAnswers.getText()+ " z " + (Integer.parseInt( lblPosition.getText())+1) + " brawo!");

            //***************************************************************88
            //TEST BAZY

            DatebaseCotroller endSocker = new DatebaseCotroller();
            System.out.println("ControlerQuiz onCheckQestion " + testName + " " + Integer.parseInt(lblCorrectAnswers.getText()));
            endSocker.setQuizName(testName);
            endSocker.setPoints(Integer.parseInt(lblCorrectAnswers.getText()));
            //  System.out.println(testName+" "+Integer.parseInt(lblCorrectAnswers.getText()));
            try {
                endSocker.start();
            } catch (Exception e) {
                Window owner = btnCheck.getScene().getWindow();
                AlertController.showAlert(Alert.AlertType.INFORMATION, owner , "Błedna scierzka ",
                              "scierzka do pliku jest błenda linia 152");
            }
            btnNextQuestion.setDisable(false);
        }

        txtAnswerA = ScoreController.setColorGoodAnswer(txtAnswerA, questions.get(positon - 1).getCorrectAnswer());
        txtAnswerB = ScoreController.setColorGoodAnswer(txtAnswerB, questions.get(positon - 1).getCorrectAnswer());
        txtAnswerC = ScoreController.setColorGoodAnswer(txtAnswerC, questions.get(positon - 1).getCorrectAnswer());
        txtAnswerD = ScoreController.setColorGoodAnswer(txtAnswerD, questions.get(positon - 1).getCorrectAnswer());
        lblPosition.setText("" + positon);
        btnCheck.setDisable(true);
    }

    @FXML
    void onNextQuestion(ActionEvent event) {
        txtAnswerA = ScoreController.resetTextToNormallFont(txtAnswerA);
        txtAnswerB = ScoreController.resetTextToNormallFont(txtAnswerB);
        txtAnswerC = ScoreController.resetTextToNormallFont(txtAnswerC);
        txtAnswerD = ScoreController.resetTextToNormallFont(txtAnswerD);

        if (!btnCheck.isDisable()) {//liczenie punktów i dodawanie w przypadku dobrej odpwiedzi
            if (ScoreController.checkCorrectAnswers(
                    checkBoxA.isSelected(),
                    checkBoxB.isSelected(),
                    checkBoxC.isSelected(),
                    checkBoxD.isSelected(),
                    txtAnswerA.getText(),
                    txtAnswerB.getText(),
                    txtAnswerC.getText(),
                    txtAnswerD.getText(),
                    questions.get(positon - 1).getCorrectAnswer())) {
                int socker;
                socker = Integer.parseInt(lblCorrectAnswers.getText())+1;
                //test
                //System.out.println(socker);
                lblCorrectAnswers.setText("" + socker);
            }
        }

        if ((positon) / questions.size() == 1) {
            System.exit(0);
        }
        lblQuestion.setText(questions.get(positon).getQuestion()); // wyswietla pytanie
        lblQuestionIsFrom.setText("pytanie" + (positon + 1) + "/" + questions.size()); //wysiwetla które to pytanie z ilu
        txtAnswerA.setText(questions.get(positon).getRandomQestion().get(0));
        txtAnswerB.setText(questions.get(positon).getRandomQestion().get(1));
        txtAnswerC.setText(questions.get(positon).getRandomQestion().get(2));
        txtAnswerD.setText(questions.get(positon).getRandomQestion().get(3));
        lblPosition.setText("" + positon);
        /*
        OLD CODE
        licznik++;
        lblQuestion.setText(FileEqualQuestionController.getQuestionFromFile(FILE, licznik));
        lblAnwserA.setText(FileEqualQuestionController.getGoodAnswerFromFile(FILE,licznik));
        lblAnwserB.setText(FileEqualQuestionController.getBadAnswer1(FILE,licznik));
        lblAnwserC.setText(FileEqualQuestionController.getBadAnswer2(FILE,licznik));
        lblAnwserD.setText(FileEqualQuestionController.getBadAnswer3(FILE, licznik));

        */
        //System.out.println((positon+1)/questions.size()+"");
        if ((positon + 1) / questions.size() == 1) {
            btnNextQuestion.setText("Zakończ");
            //TEST
            //System.out.println((positon+1)/questions.size()+"");
            btnNextQuestion.setDisable(true);
        }
        positon++;
        checkBoxA.setSelected(false);
        checkBoxB.setSelected(false);
        checkBoxC.setSelected(false);
        checkBoxD.setSelected(false);
        btnCheck.setDisable(false);

    }


    public void start() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/viewQuiz.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene viewQestion = new Scene(root);
        viewQestion.getStylesheets().add("Program/view/quizStyleView");
        primaryStage.setTitle("Quiz");
        primaryStage.setScene(viewQestion);
        primaryStage.show();

    }

    public String getOnlyNameFile(String file) {
        int lenght = file.length() - 4;
        testName = file.substring(20, lenght);
        //String name= FILE;
        return testName;
    }
}
