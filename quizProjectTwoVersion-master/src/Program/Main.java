package Program;

import Program.controller.ChangeQuizController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ChangeQuizController chaneQuiz = new ChangeQuizController();
        chaneQuiz.start();
        // QuizController quiz = new QuizController();
        // quiz.start();

    }
}
