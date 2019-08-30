package Program.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileEqualQuestionController {
    public static String getQuestionFromFile(String nameFile, int counter) {
        String question;
        if (counter == 0) {
            question = getSomeTextLine(nameFile, 1);
        } else {
            question = getSomeTextLine(nameFile, 1, counter);
        }
        return question;
    }

    public static String getGoodAnswerFromFile(String nameFile, int counter) {

        String goodAnswer;
        if (counter == 0) {
            goodAnswer = getSomeTextLine(nameFile, 2);
        } else {
            goodAnswer = getSomeTextLine(nameFile, 2, counter);
        }
        return goodAnswer;
    }

    public static String getBadAnswer1(String nameFile, int counter) {

        String badAnswer1;
        if (counter == 0) {
            badAnswer1 = getSomeTextLine(nameFile, 3);
        } else {
            badAnswer1 = getSomeTextLine(nameFile, 3, counter);
        }
        return badAnswer1;
    }

    public static String getBadAnswer2(String nameFile, int counter) {
        String badAnswer1;
        if (counter == 0) {
            badAnswer1 = getSomeTextLine(nameFile, 4);
        } else
            badAnswer1 = getSomeTextLine(nameFile, 4, counter);
        return badAnswer1;
    }

    public static String getBadAnswer3(String nameFile, int counter) {
        String badAnswer1;
        if (counter == 0) {
            badAnswer1 = getSomeTextLine(nameFile, 5);
        } else
            badAnswer1 = getSomeTextLine(nameFile, 5, counter);
        return badAnswer1;
    }

    // stworzyć obiekt line i utworzyć geter i seter

    public static String getSomeTextLine(String fileName, int moduloline) {
        File file = new File(fileName);
        int line = 0;

        String something = "nie pobrano ";

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String temporary = scanner.nextLine();
                line++;

                if (line % 6 == moduloline) {
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

    public static String getSomeTextLine(String fileName, int moduloline, int licznik) {

        File file = new File(fileName);
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

    public static int getHowMannyLineIsInFile(String fileName) {
        File file = new File(fileName);
        //TEST
        System.out.println(fileName);
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
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("nie ma takiego pliku");
            return 0;
        }
    }


}
