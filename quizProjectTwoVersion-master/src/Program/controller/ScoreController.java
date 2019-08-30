package Program.controller;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreController {
        public static boolean checkCorrectAnswers(
                boolean checkBoxA,
                boolean checkBoxB,
                boolean checkBoxC,
                boolean checkBoxD,
                String txtA,
                String txtB,
                String txtC,
                String txtD,
                String goodAnwser) {
        boolean isGoodQuestion = false;
        if (checkBoxA) {
            if (checkBoxB == false && checkBoxC == false && checkBoxD == false) {
                if (goodAnwser.equals(txtA)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQuestion = true;

                }
            }
        }
        if (checkBoxB) {
            if (checkBoxA == false && checkBoxC == false && checkBoxD == false) {
                if (goodAnwser.equals(txtB)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQuestion = true;
                }
            }
        }
        if (checkBoxC) {
            if (checkBoxB == false && checkBoxA == false && checkBoxD == false) {
                if (goodAnwser.equals(txtC)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQuestion = true;
                }
            }
        }
        if (checkBoxD) {
            if (checkBoxB == false && checkBoxC == false && checkBoxA == false) {
                if (goodAnwser.equals(txtD)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQuestion = true;
                }
            }
        }
        return isGoodQuestion;
    }

    public static Text setColorGoodAnswer(Text textQuestion, String goodAnswers) {
        if (textQuestion.getText().equals(goodAnswers)) {
            textQuestion.setFill(Color.GREEN);
            textQuestion.setFont(Font.font(16));
            textQuestion.setUnderline(true);
            textQuestion.setId("forGoodAnswers");
        } else {
            textQuestion.setFill(Color.TOMATO);
            textQuestion.setId("forBadAnswers");
            textQuestion.setFont(Font.font(12));
        }
        return textQuestion;
    }

    public static Text resetTextToNormallFont(Text textQuestion) {
        textQuestion.setFill(Color.WHITE);
        textQuestion.setFont(Font.font(14));
        textQuestion.setUnderline(false);
        textQuestion.setId("txtAnswer");
        return textQuestion;
    }

}
