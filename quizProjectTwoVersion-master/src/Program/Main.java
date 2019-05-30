package Program;

import Program.controller.ChangeQuizController;
import Program.controller.ControllerQuiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args)  {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ChangeQuizController chaneQuiz = new ChangeQuizController();
        chaneQuiz.start();
       // ControllerQuiz quiz = new ControllerQuiz();
       // quiz.start();

    }
}
