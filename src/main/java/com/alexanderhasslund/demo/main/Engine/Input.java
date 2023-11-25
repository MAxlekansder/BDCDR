package com.alexanderhasslund.demo.main.Engine;
import java.util.InputMismatchException;
import java.util.Scanner;

// se om den här ska skrivas om? <-- endast här för att kunna använda input för nu
public class Input {
    public static String stringInput() {
        String stringInput = null;
        boolean stringCheck = true;

        while (stringCheck) {
            try {
                Scanner stringScan = new Scanner(System.in);
                stringInput = stringScan.nextLine();
                stringCheck = false;

            } catch (InputMismatchException e) {
                System.out.println("Please enter a String value to continue");
            }
        }
        return stringInput;
    }


    public static int intInput() {
        int intInput = 0;
        boolean intCheck = true;

        while (intCheck) {
            try {
                Scanner intScan = new Scanner(System.in);
                intInput = intScan.nextInt();
                // String catchBlankInput = intScan.nextLine();
                if (intInput > 0) {
                    //intScan.nextLine(); verkar inte funga som tänkt <- ta bort överflödet.
                    intCheck = false;
                } else {
                    System.out.println("Input cant be empty or zero");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer to continue");
            }

        }
        return intInput;
    }
}

