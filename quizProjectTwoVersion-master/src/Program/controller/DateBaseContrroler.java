package Program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DateBaseContrroler {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static int points;
    private static String nameQuiz;
    private String nickPlayer;
    static private ControllerQuiz quiz = new ControllerQuiz();


    @FXML
    private Button btnConfing;

    @FXML
    private Button btnClose;

    @FXML
    private LineChart<?, ?> chartSocker;

    @FXML
    void btnClose(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private TextField textFieldNickName;

    @FXML
    void btnConfing(ActionEvent event){
        nickPlayer = textFieldNickName.getText();
        //TEST
        //System.out.println("btn confing in Date base controller" + nameQuiz+ "  " + points + " " +nameQuiz);
        addToBasse(textFieldNickName.getText(), getPoints(), getNameQuiz());
        chartSocker.setVisible(true);
        btnConfing.setVisible(false);
        textFieldNickName.setVisible(false);
        chartSocker.setTitle("                          Punktacja: \r\n"+nickPlayer+" zdobyłeś punktów " + points + " w quizie " + nameQuiz );
        ArrayList all = new ArrayList();
        addToChart(getToBasseIfExist(textFieldNickName.getText()));


    }
    public DateBaseContrroler(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7294306","sql7294306","2thB8edjCj");
            //con = DriverManager.getConnection("jdbc:mysql://145.14.144.78:3306/id9809359_dbquiz","id9809359_janowskiadas","root1234");
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/easyquizdb","adajano","root1234");
           //  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbquiz","root","");
            st = con.createStatement();
        }catch (Exception ex){
            System.out.println("Exeption"+ex);
        }
    }
    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/viewSocker.fxml"));
        Scene viewQestion = new Scene(loader.load());
        viewQestion.getStylesheets().add("Program/view/sockerView");
        primaryStage.setTitle("Punktacja");
        primaryStage.setScene(viewQestion);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        setPoints(points);
        setNameQuiz(nameQuiz);
        //TEST
        //  System.out.println("Start metod -" + nameQuiz+ "  "+points);
    }
    public void addToBasse(String name,int socker, String nameTest) {
       //TEST
        // System.out.println("add to base "+ socker +" "+ nameTest);
       String insertDB = "INSERT INTO punkty (imie, punkty, quiz) VALUES (?, ?, ?);";
       try {
           PreparedStatement preparedStmt = con.prepareStatement(insertDB);
            preparedStmt.setString(1 , name);
           preparedStmt.setInt(2, socker);
           preparedStmt.setString(3, nameTest);
           preparedStmt.execute();
       }catch (Exception ex){

           ex.printStackTrace(new java.io.PrintStream(System.out));
       }

    }
    public void addToChart(List<Integer> points){
        XYChart.Series series = new XYChart.Series();

        series.setName("Wyniki");
        for(Integer i = 0; i< points.size();i++) {
            try {
                series.getData().add(new XYChart.Data( ""+i, points.get(i)));
            }catch (Exception ex){
                System.out.println("tuu"+ex.getStackTrace());
            }
        }

            chartSocker.getData().add(series);

    }
    public List<Integer> getToBasseIfExist(String name) {
        List<Integer> existPointsForTheNick = new ArrayList<Integer>();
        String getBased = "SELECT * FROM punkty";
        try {
            ResultSet resultSet = rs = st.executeQuery(getBased);
            while (rs.next()) {
                if (name.equals(rs.getString("imie"))) {
                    if (nameQuiz.equals(rs.getString("quiz"))) {
                        int id = rs.getInt("id");
                        String namePlayer = rs.getString("imie");
                        int points = rs.getInt("punkty");
                        String nameQuest = rs.getString("quiz");
                        System.out.format("%s, %s, %s, %s\n", id, namePlayer, points, nameQuest);
                        existPointsForTheNick.add(points);
                    }
                }
            }
            if (name == null) {
                existPointsForTheNick.add(getPoints());
            }
        } catch (Exception ex) {
            System.out.println("getToBasseIfExist" + ex);
        }
        return existPointsForTheNick;
    }
    private ArrayList getColumntoTeable(String nameTable, int numberOfColumn){

    return  null;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        //System.out.println(points);
        this.points = points;
    }

    public String getNameQuiz() {
        return nameQuiz;
    }

    public void setNameQuiz(String nameQuiz) {
        //TEST
      //  System.out.println("ddddd"+nameQuiz);
        this.nameQuiz = nameQuiz;
    }

    public String getNickPlayer() {
        return nickPlayer;
    }

    public void setNickPlayer(String nickPlayer) {
        this.nickPlayer = nickPlayer;
    }
}

