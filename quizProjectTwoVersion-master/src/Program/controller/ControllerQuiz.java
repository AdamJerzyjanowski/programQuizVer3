package Program.controller;


import Program.model.modelQestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ControllerQuiz {

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
    private Button btnNextQestion;

    @FXML
    private Label lblQestionIsFrom;

    @FXML
    private Label lblCorectAnwsers;

    @FXML
    private Label lblPosition;

    @FXML
    private Text txtAnwserC;

    @FXML
    private Text txtAnwserD;

    @FXML
    private Text txtAnwserA;

    @FXML
    private Text txtAnwserB;


    volatile int licznik = 0;
    volatile int positon = 0;
    List<modelQestion> qestions = new ArrayList<>();
    public String nameTest;
    public int points;
   public static String file = "src/Program/pytania/pytania.txt";
    @FXML
    public void initialize() {
        if (licznik == 0) {

            /*OLD CODE
            lblQestionIsFrom.setText("1/20");
            lblQuestion.setText(ControllerFileEqualQestion.getQestionFromFile(file,licznik));
            lblAnwserA.setText(ControllerFileEqualQestion.getGoodAnswerFromFile(file,licznik));
            lblAnwserB.setText(ControllerFileEqualQestion.getBadAnwser1(file,licznik));
            lblAnwserC.setText(ControllerFileEqualQestion.getBadAnwser2(file,licznik));
            lblAnwserD.setText(ControllerFileEqualQestion.getBadAnwser3(file, licznik));
            */


            for (int i = 0; i < (ControllerFileEqualQestion.getHowMannyLineIsInFile(file) / 6); i++) {
                modelQestion tmpQestion = new modelQestion(ControllerFileEqualQestion.getQestionFromFile(file, licznik), ControllerFileEqualQestion.getGoodAnswerFromFile(file, licznik), ControllerFileEqualQestion.getBadAnwser1(file, licznik), ControllerFileEqualQestion.getBadAnwser2(file, licznik), ControllerFileEqualQestion.getBadAnwser3(file, licznik));
                qestions.add(tmpQestion);
                licznik++;
            }
            nameTest =getOnlyNameFile(file);
            Collections.shuffle(qestions);// zmienianie kolejności pytań
            lblQuestion.setText(qestions.get(positon).getQuestion()); // wyswietla pytanie
            lblQestionIsFrom.setText("pytanie " + (positon + 1) + "/" + qestions.size()); //wysiwetla które to pytanie z ilu
            txtAnwserA.setText(qestions.get(positon).getRandomQestion().get(0));
            txtAnwserB.setText(qestions.get(positon).getRandomQestion().get(1));
            txtAnwserC.setText(qestions.get(positon).getRandomQestion().get(2));
            txtAnwserD.setText(qestions.get(positon).getRandomQestion().get(3));
            positon++;
        }

    }

    @FXML
    void onCheckQestion(ActionEvent event) {
        if(ContrrollerSocker.checkCorectAnswers(checkBoxA.isSelected(), checkBoxB.isSelected(), checkBoxC.isSelected(), checkBoxD.isSelected(), txtAnwserA.getText(), txtAnwserB.getText(), txtAnwserC.getText(), txtAnwserD.getText(), qestions.get(positon-1).getCorectAnwser())){
            int socker;
            socker = Integer.parseInt(lblCorectAnwsers.getText());
            socker += 1;
            points = socker;
            //test
            //System.out.println(socker);
            lblCorectAnwsers.setText(""+socker);

        }
        if((positon)/qestions.size()==1){
            Window owner = btnCheck.getScene().getWindow();
           // AlertController.showAlert(Alert.AlertType.INFORMATION, owner , "Quiz ukończony",
           //         "Ukończyłeś "+ lblQuizTitle.getText() +"  zdobyłeś punktów "+lblCorectAnwsers.getText()+ " z " + (Integer.parseInt( lblPosition.getText())+1) + " brawo!");

           //***************************************************************88
            //TEST BAZY

            DateBaseContrroler endSocker = new DateBaseContrroler();
               System.out.println("ControlerQuiz onCheckQestion " +nameTest+" "+Integer.parseInt(lblCorectAnwsers.getText()));
               endSocker.setNameQuiz(nameTest);
               endSocker.setPoints(Integer.parseInt(lblCorectAnwsers.getText()));
          //  System.out.println(nameTest+" "+Integer.parseInt(lblCorectAnwsers.getText()));
             try { Parent parViewQestion = FXMLLoader.load(getClass().getResource("../view/viewSocker.fxml"));
                 endSocker.start();
             }catch (Exception e){}
            btnNextQestion.setDisable(false);

        }

        txtAnwserA = ContrrollerSocker.setCollorGoodAnswer(txtAnwserA, qestions.get(positon-1).getCorectAnwser());
        txtAnwserB = ContrrollerSocker.setCollorGoodAnswer(txtAnwserB, qestions.get(positon-1).getCorectAnwser());
        txtAnwserC = ContrrollerSocker.setCollorGoodAnswer(txtAnwserC, qestions.get(positon-1).getCorectAnwser());
        txtAnwserD = ContrrollerSocker.setCollorGoodAnswer(txtAnwserD, qestions.get(positon-1).getCorectAnwser());
        lblPosition.setText(""+positon);
        btnCheck.setDisable(true);
    }

    @FXML
    void onNextQestion(ActionEvent event) {
        txtAnwserA = ContrrollerSocker.resetTextToNormallFont(txtAnwserA);
        txtAnwserB = ContrrollerSocker.resetTextToNormallFont(txtAnwserB);
        txtAnwserC = ContrrollerSocker.resetTextToNormallFont(txtAnwserC);
        txtAnwserD = ContrrollerSocker.resetTextToNormallFont(txtAnwserD);

        if(btnCheck.isDisable()){//liczenie punktów i dodawanie w przypadku dobrej odpwiedzi
        }else {
            if (ContrrollerSocker.checkCorectAnswers(checkBoxA.isSelected(), checkBoxB.isSelected(), checkBoxC.isSelected(), checkBoxD.isSelected(), txtAnwserA.getText(), txtAnwserB.getText(), txtAnwserC.getText(), txtAnwserD.getText(), qestions.get(positon - 1).getCorectAnwser())) {
                int socker;
                socker = Integer.parseInt(lblCorectAnwsers.getText());
                socker += 1;
                //test
                //System.out.println(socker);
                lblCorectAnwsers.setText(""+socker);
            }
        }

        if((positon)/qestions.size()==1){
            System.exit(0);
        }
        lblQuestion.setText(qestions.get(positon).getQuestion()); // wyswietla pytanie
        lblQestionIsFrom.setText("pytanie" + (positon + 1) + "/" + qestions.size()); //wysiwetla które to pytanie z ilu
        txtAnwserA.setText(qestions.get(positon).getRandomQestion().get(0));
        txtAnwserB.setText(qestions.get(positon).getRandomQestion().get(1));
        txtAnwserC.setText(qestions.get(positon).getRandomQestion().get(2));
        txtAnwserD.setText(qestions.get(positon).getRandomQestion().get(3));
        lblPosition.setText(""+positon);
        /*
        OLD CODE
        licznik++;
        lblQuestion.setText(ControllerFileEqualQestion.getQestionFromFile(file, licznik));
        lblAnwserA.setText(ControllerFileEqualQestion.getGoodAnswerFromFile(file,licznik));
        lblAnwserB.setText(ControllerFileEqualQestion.getBadAnwser1(file,licznik));
        lblAnwserC.setText(ControllerFileEqualQestion.getBadAnwser2(file,licznik));
        lblAnwserD.setText(ControllerFileEqualQestion.getBadAnwser3(file, licznik));

        */
        //System.out.println((positon+1)/qestions.size()+"");
        if((positon+1)/qestions.size()==1){
            btnNextQestion.setText("Zakończ");
            //TEST
            //System.out.println((positon+1)/qestions.size()+"");
            btnNextQestion.setDisable(true);
        }
        positon++;
        checkBoxA.setSelected(false);
        checkBoxB.setSelected(false);
        checkBoxC.setSelected(false);
        checkBoxD.setSelected(false);
        btnCheck.setDisable(false);

    }


    public  void start() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/viewQuiz.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene viewQestion = new Scene(root);
        viewQestion.getStylesheets().add("Program/view/quizStyleView");
        primaryStage.setTitle("Quiz");
        primaryStage.setScene(viewQestion);
        primaryStage.show();

    }
public String getOnlyNameFile(String  file){
        int lenght = file.length()-4;
        nameTest = file.substring(20, lenght);
        //String name= file;
        return nameTest;
}
}
