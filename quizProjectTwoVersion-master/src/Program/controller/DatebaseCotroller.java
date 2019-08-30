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


public class DatebaseCotroller {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static int points;
    private static String quizName;
    private String playersNick;
    static private QuizController quiz = new QuizController();


    @FXML
    private Button btnConfig;

    @FXML
    private Button btnClose;

    @FXML
    private LineChart<?, ?> scoreChart;

    @FXML
    void btnClose(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private TextField textFieldNickName;

    @FXML
    void btnConfig(ActionEvent event) {
        playersNick = textFieldNickName.getText();
        //TEST
        //System.out.println("btn confing in Date base controller" + quizName+ "  " + points + " " +quizName);
        addToDatabase(textFieldNickName.getText(), getPoints(), getQuizName());
        scoreChart.setVisible(true);
        btnConfig.setVisible(false);
        textFieldNickName.setVisible(false);
        scoreChart.setTitle("                          Punktacja: \r\n" + playersNick + " zdobyłeś punktów "
                + points + " w quizie " + quizName);
        addToChart(getFromDbIfExists(textFieldNickName.getText()));
    }

    public DatebaseCotroller() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7294306","sql7294306","2thB8edjCj");
            //con = DriverManager.getConnection("jdbc:mysql://145.14.144.78:3306/id9809359_dbquiz","id9809359_janowskiadas","root1234");
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/easyquizdb", "jdbc:mysql://db4free.net:3306/easyquizdb", "root1234");
            //  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbquiz","root","");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Exeption" + ex);
        }
    }

    public void start() throws Exception {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/viewSocker.fxml"));
        Scene viewQuestion = new Scene(loader.load());
        viewQuestion.getStylesheets().add("Program/view/scoreView");
        primaryStage.setTitle("Punktacja");
        primaryStage.setScene(viewQuestion);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        setPoints(points);
        setQuizName(quizName);
        //TEST
        //  System.out.println("Start metod -" + quizName+ "  "+points);
    }

    public void addToDatabase(String name, int score, String testName) {
        //TEST
        // System.out.println("add to base "+ score +" "+ testName);
        String insertDB = "INSERT INTO punkty (imie, punkty, quiz) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(insertDB);
            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, score);
            preparedStmt.setString(3, testName);
            preparedStmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace(new java.io.PrintStream(System.out));
        }
    }

    public void addToChart(List<Integer> points) {
        XYChart.Series series = new XYChart.Series();

        series.setName("Wyniki");
        for (Integer i = 0; i < points.size(); i++) {
            try {
                series.getData().add(new XYChart.Data("" + i, points.get(i)));
            } catch (Exception ex) {
                System.out.println("tuu" + ex.getStackTrace());
            }
        }

        scoreChart.getData().add(series);
    }

    public List<Integer> getFromDbIfExists(String name) {
        List<Integer> pointsForTheNick = new ArrayList<Integer>();
        String getBased = "SELECT * FROM punkty";
        try {
            ResultSet resultSet = rs = st.executeQuery(getBased);
            while (rs.next()) {
                if (name.equals(rs.getString("imie"))) {
                    if (quizName.equals(rs.getString("quiz"))) {
                        int id = rs.getInt("id");
                        String playerName = rs.getString("imie");
                        int points = rs.getInt("punkty");
                        String quizName = rs.getString("quiz");
                        System.out.format("%s, %s, %s, %s\n", id, playerName, points, quizName);
                        pointsForTheNick.add(points);
                    }
                }
            }
            if (name == null) {
                pointsForTheNick.add(getPoints());
            }
        } catch (Exception ex) {
            System.out.println("getToBaseIfExist" + ex);
        }
        return pointsForTheNick;
    }

    private ArrayList getColumntoTeable(String nameTable, int numberOfColumn) {

        return null;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        //System.out.println(points);
        this.points = points;
    }
    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String nameQuiz) {
        //TEST
        //  System.out.println("ddddd"+quizName);
        this.quizName = nameQuiz;
    }

    public String getPlayersNick() {
        return playersNick;
    }

    public void setPlayersNick(String playersNick) {
        this.playersNick = playersNick;
    }
}

