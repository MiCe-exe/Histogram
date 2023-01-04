/**
 * Name: Michael Cervantes
 * Date: 5 November 2022
 * Explanation: Creating a Histogram from array of characters.
 */

//        Notes: Assume that the number of characters in the input file is less than or
//        equal to 200. You may also assume that each line is only one character
//        long. There will be no extra blank space after each character. Furthermore,
//        you can assume that the only characters will be capitals from ‘A’ to ‘K’.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Histogram {

    static Scanner scan = new Scanner(System.in);
    //public static int[] letterCount = new int[200];
    //public static char[] letter = new char[200];

    public static void main(String[] args) {
        String fileName = "";
        String userInput = "";
        File f = null;
        char[] letter = null;
        int[] letterCount = null;

/*
//      driver without interface
        fileName = "tq3.txt";
        letterCount = new int[200];
        letter = new char[200];

//      input one
        fileName = "tq3.txt";
        // read File
        read(letter, letterCount, fileName);

        // Sort
        sort(letter, letterCount);

        // Display
        display(letter, letterCount);

//      Input two
        fileName = "tq5.txt";
        read(letter, letterCount, fileName);

        // Sort
        sort(letter, letterCount);

        // Display
        display(letter, letterCount);
*/

        boolean quit = false;
        //Scanner scan = new Scanner(System.in);

        while(!quit)
        {
            letterCount = new int[200];
            letter = new char[200];
            //Grab file name.
            System.out.println("Input filename: ");
            fileName = getFileName();
            //System.out.println(fileName);
            //letter = new char[fileName.length() + 1];               // one way if it doesn't have to be dynamic.
            //letterCount = new int[fileName.length() + 1];           // ^^^^^^^^^ delete

            // read File
            read(letter, letterCount, fileName);

            // Sort
            sort(letter, letterCount);

            // Display
            display(letter, letterCount);

            //Check if user wants to quit if not loop back.
            scan = new Scanner(System.in);
            System.out.println("To Exit enter 'n' or any other key to continue: ");
            userInput = scan.nextLine();
            if(userInput.trim().toLowerCase().equals("n"))
            {
                quit = true;
            }
        }

        System.out.println("Now exiting");
        scan.close();
    }


    public static String getFileName()
    {
        // This method will read the filename from the user

        return scan.nextLine();        //return a string
    }


    public static void read(char[] letter, int[] letterCount, String fileName)
    {
        // This method will read the letters
        // from the filename provided in the
        // previous method
        // * USe this to populate the in[] letter count
        scan = null;
        File f = new File(fileName);

        try{
            scan = new Scanner(f);
        } catch (FileNotFoundException e){
            System.out.println("Could not find file: " + e);
        }

        String tempString = "";
        while (scan != null && scan.hasNext()){
            tempString += scan.nextLine();
        }
        //format string with no spaces;
        tempString = tempString.replaceAll("\\s", "");

        //add values and convert to char from string values
        for(int i = 0; i < tempString.length(); i++)
        {
            letter[i] = tempString.charAt(i);
            letterCount[i] = tempString.length() - tempString.replace(String.valueOf(letter[i]),"").length();
        }

    }

    public static void sort(char[] letter, int[] letterCount)
    {
        //This method will sort letterCount keeping letter synchronized

        int MAX = 0;

        // Get length
        for(int i = 0; i < letter.length; i++)
        {
            if(letter[i] != '\u0000')
            {
                MAX++;
            }
            else{
                i = letter.length;
            }
        }

        // Sort Alpha n^2 :-/
        for(int i = 0; i < MAX - 1; i++){                   // MAX - 1
            for(int j = 0; j < MAX - 1 - i; j++){       // MAX - 1 - i
                if(letter[j] > letter[j + 1]){
                    // swap char first
                    int tempChar = letter[j];
                    letter[j] = letter[j + 1];
                    letter[j + 1] = (char) tempChar;

                    // swap counter
                    int tempNum = letterCount[j];
                    letterCount[j] = letterCount[j + 1];
                    letterCount[j + 1] = tempNum;
                }
            }
        }

        //sort numeric n^2

        for(int i = 0; i < MAX; i++){
            for(int j = i; j > 0; j--){
                if(letterCount[j] < letterCount[j - 1]){
                    //swap swap char
                    int tempChar = letter[j];
                    letter[j] = letter[j - 1];
                    letter[j - 1] = (char)tempChar;

                    //swap int
                    int tempNum = letterCount[j];
                    letterCount[j] = letterCount[j - 1];
                    letterCount[j - 1] = tempNum;
                }
            }
        }

        // Trim
        char[] tempL = new char[letter.length];
        int[] tempLc = new int[letterCount.length];
        int j = 0;

        // Trim - Copy to temp
        for(int i = 0; i < MAX; i++){
            if(letter[i] == '\u0000')
            {
                break;
            }

            if(letterCount[i] > 1){
                tempLc[i] = letterCount[j];
                tempL[i] = letter[j];

                j += letterCount[j];
            } else {
                tempLc[i] = letterCount[j];
                tempL[i] = letter[j];

                j++;
            }
        }

        // copy the trimmed array over
        for(int i = 0; i < tempL.length; i++) {
            letter[i] = tempL[i];
            letterCount[i] = tempLc[i];
        }

    }

    public static void display(char[] letter, int[] letterCount)
    {
        //this method will display the histogram

        // Get char that have 0 value
        String zeroLetters = "ABCDEFGHIJK";
        int MAX = zeroLetters.length();             // Get MAX string length
        for(int i = 0; i < letter.length; i++)
        {
            if(letter[i] == '\u0000')
            {
                break;
            }
            zeroLetters =  zeroLetters.replace(String.valueOf(letter[i]), "");
        }

        // Get trimmed MAX string length
        MAX -= zeroLetters.length();

//        display list
        System.out.println("Char occurrence");
        for(int i = 0; i < letter.length; i++)
        {
            if(letter[i] == '\u0000'){
                break;
            }

            System.out.println(String.valueOf(letter[i]) + " " + letterCount[i]);

        }

        //Alphabet list
        String finalString = new String(new char[7]).replace("\0", " ");

        for(int i = 0; i < zeroLetters.length(); i++)
        {
            finalString += zeroLetters.charAt(i) + " ";
        }

        for(int i = 0; i < MAX; i++)
        {
            finalString += letter[i] + " ";
        }

        //Top border
        System.out.printf("\n");
        System.out.println(new String(new char[29]).replace("\0", "="));

        // Building Histogram
        String charBuilder = String.valueOf(letter[MAX - 1]);
        for(int i = MAX - 1; i > -1; i--){

            //spacer for letters;
            for(int j = MAX - 2; j >= 0; j-- ){

                if(letterCount[j] < letterCount[i]){
                    i = j + 1;
                    break;
                }

                if(j == 0){
                    i = 0;
                }

                if(letterCount[j] == letterCount[i]) {
                    charBuilder = String.valueOf(letter[j]) + " " + charBuilder;
                }
            }

//          Padding for string output.
            int spacer = finalString.length() - finalString.replace(charBuilder, "").length() + 1;
            String padding = new String(new char[4]).replace("\0", " ");

//          Build and Print out string(s)
            String builder = "|"+ padding + String.valueOf(letterCount[i]) +"|" +
                    new String(new char[22 - spacer]).replace("\0", " ") + charBuilder;

            System.out.println(builder);

//          check x to 0 gap
            int loop = letterCount[i] - 1;
            // correct n-1 loop
            if( loop == letter[i] && letterCount[i] > 1){
                while(loop > 1){
                    loop--;

                    System.out.println("|" + padding + String.valueOf(loop) + "|" +
                            new String(new char[22 - spacer]).replace("\0", " ") + charBuilder);
                }
            }

            // check for gap greater than 1 and 0
            int gap = 0;
            if(i - 1 >= 0){                    //if(i - 1 != 0 && i - 1 > 0){
                gap = letterCount[i] - letterCount[i - 1] ;
            }

            if( i != 0 && gap > 1){
                for(int j = gap; j > 1; j--) {

                    //correct string builder padding


                    System.out.println("|" + padding + String.valueOf(loop) + "|" +
                            new String(new char[22 - spacer]).replace("\0", " ") + charBuilder);

                    loop--;
                }
            }
        }

        //Bottom boarder
        System.out.println(new String(new char[29]).replace("\0", "-"));

        System.out.println(finalString);
    }
}
