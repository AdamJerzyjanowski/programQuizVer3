package Program.controller;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ContrrollerSocker {
    public static boolean checkCorectAnswers(boolean checkBoxA, boolean checkBoxB, boolean checkBoxC, boolean checkBoxD, String txtA, String txtB, String txtC, String txtD, String goodAnwser) {
        boolean isGoodQestion = false;
        if (checkBoxA) {
            if (checkBoxB==false && checkBoxC==false && checkBoxD==false) {
                if (goodAnwser.equals(txtA)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQestion = true;

                }
            }
        }
        if (checkBoxB) {
            if (checkBoxA==false && checkBoxC==false && checkBoxD==false) {
                if (goodAnwser.equals(txtB)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQestion = true;
                }
            }
        }
        if (checkBoxC) {
            if (checkBoxB==false && checkBoxA==false && checkBoxD==false) {
                if (goodAnwser.equals(txtC)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQestion = true;
                }
            }
        }
        if (checkBoxD) {
            if (checkBoxB==false && checkBoxC==false && checkBoxA==false) {
                if (goodAnwser.equals(txtD)) {
                    //TEST
                    //System.out.println("zdobyłeś punkt");
                    isGoodQestion = true;
                }
            }
        }
        return isGoodQestion;
    }

   public static Text setCollorGoodAnswer(Text textquestion, String goodAnswers) {
       if (textquestion.getText().equals(goodAnswers)) {
           textquestion.setFill(Color.GREEN);
           textquestion.setFont(Font.font(16));
           textquestion.setUnderline(true);
           textquestion.setId("forGoodAnswers");
           //textquestion.

       }
       else {
           textquestion.setFill(Color.TOMATO);
           textquestion.setId("forBadAnswers");
           textquestion.setFont(Font.font(  12) );
       }
        return textquestion;
   }
   public static Text resetTextToNormallFont(Text textquestion){
       textquestion.setFill(Color.WHITE);
       textquestion.setFont(Font.font(  14) );
       textquestion.setId(null);
       textquestion.setUnderline(false);
       textquestion.setId("txtAnwser");
       return textquestion;
   }

}
