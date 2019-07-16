package Program.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ControllerFileEqualQestion {
    public static String getQestionFromFile(String nameFile, int licznik) {
       String qestion;
        if (licznik == 0) {
            qestion = getSomeTextLine(nameFile,1);
        }
        else {
            qestion = getSomeTextLine(nameFile, 1, licznik);
        }
        return qestion;
    }

    public static String getGoodAnswerFromFile(String nameFile, int licznik) {

        String goodAnwser;
        if(licznik==0) {
            goodAnwser = getSomeTextLine(nameFile, 2);
        }
        else{
            goodAnwser = getSomeTextLine(nameFile,2,licznik);
        }
        return goodAnwser;
    }

    public static String getBadAnwser1(String nameFile, int licznik) {

        String badAnwser1;
        if(licznik == 0) {
            badAnwser1 = getSomeTextLine(nameFile, 3);
        }
        else {
            badAnwser1 = getSomeTextLine(nameFile,3,licznik);
        }
        return badAnwser1;
    }
    public static String getBadAnwser2(String nameFile, int licznik) {
        String badAnwser1;
        if (licznik == 0) {
            badAnwser1 = getSomeTextLine(nameFile,4);
        }else
        badAnwser1 = getSomeTextLine(nameFile, 4, licznik);
        return badAnwser1;
    }

    public static String getBadAnwser3(String nameFile, int licznik) {
        String badAnwser1;
        if (licznik == 0) {
            badAnwser1 = getSomeTextLine(nameFile,5);
        }else
        badAnwser1 = getSomeTextLine(nameFile, 5, licznik);
        return badAnwser1;
    }

    // stworzyć obiekt line i utworzyć geter i seter
    public static String getSomeTextLine(String nameFile, int moduloline) {
        File file = new File(nameFile);
        int line = 0;


        String something = "nie pobrano ";

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                String temporary = scanner.nextLine();
                line++;

                if (line %6 == moduloline) {
                    something = temporary;
                    return something;


                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono pliku");
        // AlertController alert = new AlertController();
       //     alert.showAlert( Alert.AlertType.WARNING, Stage stage , "UWAGA","nie znaleziono pliku");
            return "nie znaleziono pliku";

        }
        return something;
    }

    public static String getSomeTextLine(String nameFile, int moduloline, int licznik) {

        File file = new File(nameFile);
        int line = 0;


        String something = "nie pobrano ";

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                String temporary = scanner.nextLine();

                line++;
                //TEST
               // System.out.println(line);
               // System.out.println(line % 6);

                    if (line >= 6 * licznik && line % 6 == moduloline) {
                        something = temporary;
                        return something;


                    }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono pliku");
            return "nie znaleziono pliku";
        }
        return something;
    }
   public static int getHowMannyLineIsInFile(String nameFile){
        File file = new File(nameFile);
        //TEST
       System.out.println(nameFile);
       System.out.println(file);
        try {
            Scanner scanner = new Scanner(file);
            int lineFile = 0;
            while (scanner.hasNext()) {
                scanner.nextLine();
                lineFile++;
                //TEST
                System.out.println(lineFile);
            }
            return lineFile;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("nie ma takiego pliku");
            return 0;
        }
   }


}
