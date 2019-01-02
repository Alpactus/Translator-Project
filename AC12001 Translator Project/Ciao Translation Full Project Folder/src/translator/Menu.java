/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

/**
 *
 * @author lboyl
 */
public class Menu {

    /**
     * Main Class
     * 
     * Contains the menu and allows the user to access the program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       

        Translator translator = new Translator();
        translator.OpenDictionary();
        int userChoice = 0;
        System.out.println("Welcome to the Ciao Translations");
        do {

            System.out.println("Please select a choice of what you wish to do");
            System.out.println("1) Display Dictionaries");
            System.out.println("2) Translate");
            System.out.println("3) Translate File");
            System.out.println("4) Add to dictionary");
            System.out.println("5) Remove from dictionaries");
            System.out.println("6) Quit");

            userChoice = Genio.getInteger();
            switch (userChoice) {
                case 1:
                    translator.DisplayDictionaries();
                    break;
                case 2:
                    translator.translate();
                    break;
                case 3:
                    translator.translateFile( translator.openTextFile());
                    break;
                case 4:
                    translator.addToDictionary();
                    break;
                case 5:
                    translator.removeWords();
                    break;
                case 6:
                    System.out.println("Thank you for using Ciao Translations");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sorry, that isn't a valid input");
                    break;
            }
        } while (userChoice != 6);
    }
}
