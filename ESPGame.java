/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: (Computer Science I)
 * Due: 09/15/2025
 * * Platform/compiler: Eclipse 
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Renata Podlesny
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ESPGame {

    public static void main(String[] args) { 
        int maxColors, score = 0;
        boolean continueGame = true;
        String computerColor = "", userGuess;
        //all the colors we will fill later thru the file
        String color1 = "", color2 = "", color3 = "", color4 = "", color5 = "", color6 = "", color7 = "", color8 = "", color9 = "", color10 = "", color11 = "", color12 = "", color13 = "", color14 = "", color15 = "", color16 = "";
        int choice;
        String filename;

        //intro welcome 
        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");

        Scanner keyboard = new Scanner(System.in); //make object to get input

        //plays game once, then repeats if user wants to play again
        do {
            //choose which game version to do
            do {
                System.out.println("\nWould you please choose one of the 4 options from the menu:");
                System.out.println("1- read and display on the screen first 16 names of colors from a file colors.txt, so the player can select one of them names of colors.\n"
                        + "2- read and display on the screen first 10 names of colors from a file colors.txt, so the player can select one of them names of colors.\n"
                        + "3- read and display on the screen first 5 names of colors from a file colors.txt, so the player can select one of them names of colors.\n"
                        + "4- Exit from the program\n");
                System.out.print("Enter the option: ");

                //makes sure user enters a number
                if (keyboard.hasNextInt()) 
                    choice = keyboard.nextInt();
                 else {
                    choice = 0; //so that it triggers the invalid entry loop
                }
                
                keyboard.nextLine(); 
            } while (choice < 1 || choice > 4); //repeat if user didn't enter 1-4
            
            
            if (choice != 4) {  //because if it is 4 they are ending the game and no file is needed
                System.out.print("Enter the filename: ");
                filename = keyboard.nextLine();

                try {
                    File myfile = new File(filename); 
                    Scanner inputFile = new Scanner(myfile); //now our file is created and accessible

                    //store all color values from file to variables for easy access
                    color1 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color2 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color3 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color4 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color5 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color6 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color7 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color8 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color9 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color10 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color11 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color12 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color13 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color14 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color15 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";
                    color16 = inputFile.hasNextLine() ? inputFile.nextLine().trim() : "";

                    inputFile.close();
                } catch (IOException e) {
                    System.out.println("Sorry, there was an error opening the file. Please try again.");
                    continue; //starts the loop from the start
                }
            }

            score = 0; //score starts from 0 at start of all games
            switch (choice) {
                case 1: {
                    maxColors = 16;
                    System.out.println("There are " + maxColors + " colors from a file:");
                    System.out.println("1 " + color1);
                    System.out.println("2 " + color2);
                    System.out.println("3 " + color3);
                    System.out.println("4 " + color4);
                    System.out.println("5 " + color5);
                    System.out.println("6 " + color6);
                    System.out.println("7 " + color7);
                    System.out.println("8 " + color8);
                    System.out.println("9 " + color9);
                    System.out.println("10 " + color10);
                    System.out.println("11 " + color11);
                    System.out.println("12 " + color12);
                    System.out.println("13 " + color13);
                    System.out.println("14 " + color14);
                    System.out.println("15 " + color15);
                    System.out.println("16 " + color16);

                    //system can pick a random color
                    Random rand = new Random();

                    //3 rounds of the game
                    for (int round = 1; round <= 3; round++) {
                        System.out.println("Round " + round);

                        int randomNumber = rand.nextInt(maxColors) + 1; //random color is generated and then assigned from number to color
                        switch (randomNumber) {
                            case 1:
                                computerColor = color1;
                                break;
                            case 2:
                                computerColor = color2;
                                break;
                            case 3:
                                computerColor = color3;
                                break;
                            case 4:
                                computerColor = color4;
                                break;
                            case 5:
                                computerColor = color5;
                                break;
                            case 6:
                                computerColor = color6;
                                break;
                            case 7:
                                computerColor = color7;
                                break;
                            case 8:
                                computerColor = color8;
                                break;
                            case 9:
                                computerColor = color9;
                                break;
                            case 10:
                                computerColor = color10;
                                break;
                            case 11:
                                computerColor = color11;
                                break;
                            case 12:
                                computerColor = color12;
                                break;
                            case 13:
                                computerColor = color13;
                                break;
                            case 14:
                                computerColor = color14;
                                break;
                            case 15:
                                computerColor = color15;
                                break;
                            case 16:
                                computerColor = color16;
                                break;
                        }

                        //gameplay
                        System.out.println("I am thinking of a color.\n"
                                + "Is it one of list of colors above?  \n"
                                + "Enter your guess: \n");
                        userGuess = keyboard.nextLine();

                        System.out.println("I was thinking of " + computerColor);
                        score += computerColor.equalsIgnoreCase(userGuess) ? 1 : 0;
                    }

                    System.out.println("Game Over!");
                    System.out.println("You guessed " + score + " out of 3 colors correctly.");
                    break;
                }
                case 2: {
                    maxColors = 10;
                    System.out.println("There are " + maxColors + " colors from a file:");
                    System.out.println("1 " + color1);
                    System.out.println("2 " + color2);
                    System.out.println("3 " + color3);
                    System.out.println("4 " + color4);
                    System.out.println("5 " + color5);
                    System.out.println("6 " + color6);
                    System.out.println("7 " + color7);
                    System.out.println("8 " + color8);
                    System.out.println("9 " + color9);
                    System.out.println("10 " + color10);

                    Random rand = new Random();

                    for (int round = 1; round <= 3; round++) {
                        System.out.println("Round " + round);

                        int randomNumber = rand.nextInt(maxColors) + 1;
                        switch (randomNumber) {
                            case 1:
                                computerColor = color1;
                                break;
                            case 2:
                                computerColor = color2;
                                break;
                            case 3:
                                computerColor = color3;
                                break;
                            case 4:
                                computerColor = color4;
                                break;
                            case 5:
                                computerColor = color5;
                                break;
                            case 6:
                                computerColor = color6;
                                break;
                            case 7:
                                computerColor = color7;
                                break;
                            case 8:
                                computerColor = color8;
                                break;
                            case 9:
                                computerColor = color9;
                                break;
                            case 10:
                                computerColor = color10;
                                break;
                        }

                        //gameplay
                        System.out.println("I am thinking of a color.\n"
                                + "Is it one of list of colors above?  \n"
                                + "Enter your guess: \n");
                        userGuess = keyboard.nextLine();

                        System.out.println("I was thinking of " + computerColor);
                        score += computerColor.equalsIgnoreCase(userGuess) ? 1 : 0;
                    }

                    System.out.println("Game Over");
                    System.out.println("You guessed " + score + " out of 3 colors correctly.");
                    break;
                }
                case 3: {
                    maxColors = 5;
                    System.out.println("There are " + maxColors + " colors from a file:");
                    System.out.println("1 " + color1);
                    System.out.println("2 " + color2);
                    System.out.println("3 " + color3);
                    System.out.println("4 " + color4);
                    System.out.println("5 " + color5);

                    Random rand = new Random();

                    for (int round = 1; round <= 3; round++) {
                        System.out.println("Round " + round);

                        int randomNumber = rand.nextInt(maxColors) + 1;
                        switch (randomNumber) {
                            case 1:
                                computerColor = color1;
                                break;
                            case 2:
                                computerColor = color2;
                                break;
                            case 3:
                                computerColor = color3;
                                break;
                            case 4:
                                computerColor = color4;
                                break;
                            case 5:
                                computerColor = color5;
                                break;
                        }

                        //gameplay
                        System.out.println("I am thinking of a color.\n"
                                + "Is it one of list of colors above?  \n"
                                + "Enter your guess: \n");
                        userGuess = keyboard.nextLine();

                        System.out.println("I was thinking of " + computerColor);
                        score += computerColor.equalsIgnoreCase(userGuess) ? 1 : 0;
                    }

                    System.out.println("Game Over");
                    System.out.println("You guessed " + score + " out of 3 colors correctly.");
                    break;
                }
                case 4:
                    //they are done with the game
                    continueGame = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }

            //if they want to play again...
            System.out.print("Would you like to continue a Game? Type Yes/No: ");
            String response = keyboard.nextLine();
            continueGame = response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("Y"); //sets contiunueGame to true if they responded yes in any way

        } while (continueGame == true);
        
        //Final info
        System.out.println("Username: Renata Podlesny");
        System.out.println("I am a CMSC student with a major in Computer Science");
        System.out.println("09/15/25");

        keyboard.close();
    }
}