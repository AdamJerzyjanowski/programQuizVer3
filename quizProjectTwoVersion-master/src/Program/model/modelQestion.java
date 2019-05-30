package Program.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class modelQestion {
    private String question;
    private String corectAnwser;
    private String badAnwser1;
    private String badAnwser2;
    private String badAnwser3;


    private List<String> randomQestion = new ArrayList<>();

    public modelQestion(){}
    public modelQestion(String question, String corectAnwser, String badAnwser1, String badAnwser2, String badAnwser3){
        this.question = question;
        this.corectAnwser = corectAnwser;
        this.badAnwser1 = badAnwser1;
        this.badAnwser2 = badAnwser2;
        this.badAnwser3 = badAnwser3;
        randomQestion.add(corectAnwser);
        randomQestion.add(badAnwser1);
        randomQestion.add(badAnwser2);
        randomQestion.add(badAnwser3);
        Collections.shuffle(randomQestion);
    }
    public List<String> getRandomQestion() {
        return randomQestion;
    }


    public String getBadAnwser1() {
        return badAnwser1;
    }

    public void setBadAnwser1(String badAnwser1) {
        this.badAnwser1 = badAnwser1;
    }



    public String getBadAnwser2() {
        return badAnwser2;
    }

    public void setBadAnwser2(String badAnwser2) {
        this.badAnwser2 = badAnwser2;
    }

    public String getBadAnwser3() {
        return badAnwser3;
    }

    public void setBadAnwser3(String badAnwser3) {
        this.badAnwser3 = badAnwser3;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public String getCorectAnwser() {
        return corectAnwser;
    }

    public void setCorectAnwser(String corectAnwser) {
        this.corectAnwser = corectAnwser;
    }



}
