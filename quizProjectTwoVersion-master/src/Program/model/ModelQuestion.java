package Program.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ModelQuestion {
    private String question;
    private String correctAnswer;
    private String badAnswer1;
    private String badAnswer2;
    private String badAnswer3;


    private List<String> randomQestion = new ArrayList<>();

    public ModelQuestion() {
    }

    public ModelQuestion(String question, String corectAnwser, String badAnwser1, String badAnwser2, String badAnwser3) {
        this.question = question;
        this.correctAnswer = corectAnwser;
        this.badAnswer1 = badAnwser1;
        this.badAnswer2 = badAnwser2;
        this.badAnswer3 = badAnwser3;
        randomQestion.add(corectAnwser);
        randomQestion.add(badAnwser1);
        randomQestion.add(badAnwser2);
        randomQestion.add(badAnwser3);
        Collections.shuffle(randomQestion);
    }

    public List<String> getRandomQestion() {
        return randomQestion;
    }


    public String getBadAnswer1() {
        return badAnswer1;
    }

    public void setBadAnswer1(String badAnswer1) {
        this.badAnswer1 = badAnswer1;
    }


    public String getBadAnswer2() {
        return badAnswer2;
    }

    public void setBadAnswer2(String badAnswer2) {
        this.badAnswer2 = badAnswer2;
    }

    public String getBadAnswer3() {
        return badAnswer3;
    }

    public void setBadAnswer3(String badAnswer3) {
        this.badAnswer3 = badAnswer3;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
