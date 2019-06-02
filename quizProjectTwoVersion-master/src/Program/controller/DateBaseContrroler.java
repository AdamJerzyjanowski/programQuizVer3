package Program.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;


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
        getToBasseIfExist("adam");
        addToBasse(textFieldNickName.getText(), getPoints(), getNameQuiz());
        getToBasseIfExist("adam");
        chartSocker.setVisible(true);
        btnConfing.setVisible(false);
        chartSocker.setTitle("                          Punktacja: \r\n"+nickPlayer+" zdobyłeś punktów " + points + " w quizie " + nameQuiz );
        ArrayList all = new ArrayList();

    }
  /* // public DateBaseContrroler(int points, String nameQuiz) {
        this.points = points;
        this.nameQuiz = nameQuiz;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbquiz", "root", "");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Exeption" + ex);
        }
    }*/


    public DateBaseContrroler(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://145.14.144.78:3306/id9809359_dbquiz","id9809359_janowskiadas","root1234");   //  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbquiz","root","");
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
           System.out.println("add to baSE METOD");
           //TEST
           //System.out.println(nameTest+" " +socker);
           //System.out.println(points + "  "+nameQuiz);
           PreparedStatement preparedStmt = con.prepareStatement(insertDB);
           //System.out.println(nameTest + nameQuiz);
            preparedStmt.setString(1 , name);
           preparedStmt.setInt(2, socker);
           preparedStmt.setString(3, nameTest);

           //int n = preparedStmt.executeUpdate();
           preparedStmt.execute();
         //  preparedStmt.close();
          // con.close();

       }catch (Exception ex){

           ex.printStackTrace(new java.io.PrintStream(System.out));
       }

    }
    public  ArrayList getToBasseIfExist(String name) {
        ArrayList existNick = new ArrayList<String>();
        String getBased = "SELECT * FROM punkty";
        try {
            ResultSet resultSet = rs = st.executeQuery(getBased);

            while (rs.next()){
                int id = rs.getInt("id");
                String namePlayer = rs.getString("imie");
                int points = rs.getInt("punkty");
                String nameQuest = rs.getString("quiz");
                System.out.format("%s, %s, %s, %s\n", id, namePlayer, points, nameQuest);
existNick.add(namePlayer);
            }
//            st.close();

        if (name == null) {


        }}catch(Exception ex){
            System.out.println("getToBasseIfExist" + ex);
        }
            ArrayList exampe = new ArrayList<String>();
            System.out.println(exampe);
            return exampe;
        }

    public  ArrayList getPointsToIfExist(String name){
        ArrayList existNick = new ArrayList<String>();
        String getBased =  "SELECT * FROM `punkty`";
        try {ResultSet resultSet = rs = st.executeQuery(getBased) ;
        }catch (Exception ex){

        }
        if(name==null){

        }
        ArrayList exampe = new ArrayList<String>();

        return exampe;
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
        System.out.println("ddddd"+nameQuiz);
        this.nameQuiz = nameQuiz;
    }

    public String getNickPlayer() {
        return nickPlayer;
    }

    public void setNickPlayer(String nickPlayer) {
        this.nickPlayer = nickPlayer;
    }
}

