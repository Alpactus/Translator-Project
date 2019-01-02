/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author lboyl
 */
public class Translator {

    //Creates a TreeMap for each language contaned in the program.
    private TreeMap<String, String[]> dictionariesEnglishTo = new TreeMap<String, String[]>();
    private TreeMap<String, String[]> dictionariesGermanTo = new TreeMap<String, String[]>();
    private TreeMap<String, String[]> dictionariesFrenchTo = new TreeMap<String, String[]>();
    private TreeMap<String, String[]> dictionariesSpanishTo = new TreeMap<String, String[]>();
    private TreeMap<String, String[]> dictionariesItalianTo = new TreeMap<String, String[]>();

    Translator() {

    }

    /**
     * Open Dictionary Method
     *
     * Opens and loads the dictionaries into the program. This is done using
     * buffered readers and assign the read values to a treemap for each
     * language
     */
    public void OpenDictionary() {
        try {
            //Opens tha buffered reader for each language
            BufferedReader english = new BufferedReader(new FileReader("endictionary.txt"));
            BufferedReader german = new BufferedReader(new FileReader("dedictionary.txt"));
            BufferedReader french = new BufferedReader(new FileReader("frdictionary.txt"));
            BufferedReader spanish = new BufferedReader(new FileReader("esdictionary.txt"));
            BufferedReader italian = new BufferedReader(new FileReader("itdictionary.txt"));

            String englishWord, germanWord, frenchWord, spanishWord, italianWord;

            while ((englishWord = english.readLine()) != null) {
                germanWord = german.readLine();
                frenchWord = french.readLine();
                spanishWord = spanish.readLine();
                italianWord = italian.readLine();

                String[] translations = new String[5];
                translations[0] = englishWord;
                translations[1] = germanWord;
                translations[2] = frenchWord;
                translations[3] = spanishWord;
                translations[4] = italianWord;

                //puts both the word and the array of translations into each map. The language word is the key and the array is the value
                dictionariesEnglishTo.put(englishWord, translations);
                dictionariesGermanTo.put(germanWord, translations);
                dictionariesFrenchTo.put(frenchWord, translations);
                dictionariesSpanishTo.put(spanishWord, translations);
                dictionariesItalianTo.put(italianWord, translations);
            }

            //Closes each of the readers
            english.close();
            german.close();
            french.close();
            spanish.close();
            italian.close();

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        } catch (IOException e) {
            System.out.println("There has been a reading error");
        }

    }

    /**
     * Display Dictionaries Method
     *
     * This method displays the dictionaries to the user. This allows the user
     * to see the two dictionaries they have chosen. This is done by making a
     * set copy of the tree map and iterating through it
     */
    public void DisplayDictionaries() {
        //Initiates the variables
        Set set;
        String firstLang = "";
        String secondLang = "";
        Iterator itr = null;
        int userChoiceFirst = 0;
        System.out.println("Please select the first language to display");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    //Creates a copy of the dictionary as a set
                    set = dictionariesEnglishTo.entrySet();
                    //Creates an iterator from the set
                    itr = set.iterator();
                    firstLang = "English";
                    break;
                case 2:
                    set = dictionariesGermanTo.entrySet();
                    itr = set.iterator();
                    firstLang = "German";
                    break;
                case 3:
                    set = dictionariesFrenchTo.entrySet();
                    itr = set.iterator();
                    firstLang = "French";
                    break;
                case 4:
                    set = dictionariesSpanishTo.entrySet();
                    itr = set.iterator();
                    firstLang = "Spanish";
                    break;
                case 5:
                    set = dictionariesItalianTo.entrySet();
                    itr = set.iterator();
                    firstLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 1 || userChoiceFirst > 5);

        //Asks the user for the second language they would like to display
        System.out.println("Please select your choice for the second language to display");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");

        int userChoiceSecond = 0;
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    userChoiceSecond = 0;
                    secondLang = "English";
                    break;
                case 2:
                    userChoiceSecond = 1;
                    secondLang = "German";
                    break;
                case 3:
                    userChoiceSecond = 2;
                    secondLang = "French";
                    break;
                case 4:
                    userChoiceSecond = 3;
                    secondLang = "Spanish";
                    break;
                case 5:
                    userChoiceSecond = 4;
                    secondLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 0 || userChoiceFirst > 5);

        while (itr.hasNext()) {
            //Creates a map entry copy of the dictionary
            Map.Entry mentry = (Map.Entry) itr.next();
            //Takes the value of the entry map and assigns it to an array
            String[] array = (String[]) mentry.getValue();
            System.out.print(firstLang + ": " + mentry.getKey() + "||" + secondLang + ": ");
            System.out.println(array[userChoiceSecond]);
        }
        System.out.println("");
        System.out.println("");
    }

    /**
     * Translate Method
     *
     * This method translates the phrase the user enters into the language of
     * their choice. It uses the processTranslation method to do this
     */
    public void translate() {
        TreeMap temp = null;
        String phraseToTranslate;
        String firstLang = "";
        String secondLang = "";

        int userChoiceFirst = 0;
        System.out.println("Please select the first language to display");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    //Creates a temporary copy of the the dictionary
                    temp = this.dictionariesEnglishTo;
                    firstLang = "English";
                    break;
                case 2:
                    temp = this.dictionariesGermanTo;
                    firstLang = "German";
                    break;
                case 3:
                    temp = this.dictionariesFrenchTo;
                    firstLang = "French";
                    break;
                case 4:
                    temp = this.dictionariesSpanishTo;
                    firstLang = "Spanish";
                    break;
                case 5:
                    temp = this.dictionariesItalianTo;
                    firstLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 1 || userChoiceFirst > 5);

        System.out.println("Please enter what you wish to translate");
        phraseToTranslate = Genio.getString();

        System.out.println("Please select your choice for the language to translate to");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");

        int userChoiceSecond = 0;
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    userChoiceSecond = 0;
                    secondLang = "English";
                    break;
                case 2:
                    userChoiceSecond = 1;
                    secondLang = "German";
                    break;
                case 3:
                    userChoiceSecond = 2;
                    secondLang = "French";
                    break;
                case 4:
                    userChoiceSecond = 3;
                    secondLang = "Spanish";
                    break;
                case 5:
                    userChoiceSecond = 4;
                    secondLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 0 || userChoiceFirst > 5);

        //Calls the processTranslation method
        String translatedPhrase = this.processTranslation(userChoiceSecond, phraseToTranslate, temp);
        System.out.println(firstLang + ": " + phraseToTranslate);
        System.out.println(secondLang + ": " + translatedPhrase);
    }

    /**
     * Process Translation Method
     *
     * This method processes the phrase that the user has entered. It translates
     * it from the entered language to the language the user has chosen.
     *
     * @param userChoice
     * @param phraseToTranslate
     * @param dictionary
     * @return
     */
    public String processTranslation(int userChoice, String phraseToTranslate, TreeMap dictionary) {
        String translatedPhrase = "";

        long finishTime = 0;
        long executionTime = 0;

        //Puts the current time in milli seconds
        long startTime = System.currentTimeMillis();

        //Replaces all punctuation in the entered phrase and changes it to lower case
        phraseToTranslate = phraseToTranslate.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        //Splits the phrase into separate words and places them into the array
        String[] phrase = phraseToTranslate.split(" ");

        String[] translations = new String[5];
        if (userChoice == 0) {
            System.out.println("");
            for (int j = 0; j < phrase.length; j++) {
                //Retreives the value from the dictionary
                translations = (String[]) dictionary.get(phrase[j]);

                //Compares the translations array. If it is null or if the value at 0 is blank, it continues
                if (translations == null || translations[0] == " ") {
                    translatedPhrase = translatedPhrase + phrase[j] + " ";

                } else {
                    translatedPhrase = translatedPhrase + translations[0] + " ";

                }
//Changes the phrase to be capitalised
                translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

            }
        } else if (userChoice == 1) {
            System.out.println("");
            for (int j = 0; j < phrase.length; j++) {
                translations = (String[]) dictionary.get(phrase[j]);

                if (translations == null || translations[1] == " ") {
                    translatedPhrase = translatedPhrase + phrase[j] + " ";

                } else {
                    translatedPhrase = translatedPhrase + translations[1] + " ";

                }

                translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

            }
        } else if (userChoice == 2) {
            System.out.println("");
            for (int j = 0; j < phrase.length; j++) {
                translations = (String[]) dictionary.get(phrase[j]);

                if (translations == null || translations[2] == " ") {
                    translatedPhrase = translatedPhrase + phrase[j] + " ";

                } else {
                    translatedPhrase = translatedPhrase + translations[2] + " ";

                }

                translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

            }
        } else if (userChoice == 3) {
            System.out.println("");
            for (int j = 0; j < phrase.length; j++) {
                translations = (String[]) dictionary.get(phrase[j]);

                if (translations == null || translations[3] == " ") {
                    translatedPhrase = translatedPhrase + phrase[j] + " ";

                } else {
                    translatedPhrase = translatedPhrase + translations[3] + " ";

                }

                translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

            }
        } else if (userChoice == 4) {
            System.out.println("");
            for (int j = 0; j < phrase.length; j++) {
                translations = (String[]) dictionary.get(phrase[j]);

                if (translations == null || translations[4] == " ") {
                    translatedPhrase = translatedPhrase + phrase[j] + " ";

                } else {
                    translatedPhrase = translatedPhrase + translations[4] + " ";

                }

                translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

            }
        }
        //Takes the current elapsed time
        finishTime = System.currentTimeMillis();
        //Subtracts the start time from the finish time to work out how long the translation took
        executionTime = (finishTime - startTime);

        System.out.println(" ");
        System.out.println("This translation took: " + executionTime + " milli seconds");

        return translatedPhrase;
    }

    /**
     * Translate File Method
     *
     * This method translates an external file into the language of the users
     * choice. It has the text file passed in from the Open Text File method
     *
     * @param textFile
     */
    public void translateFile(String[] textFile) {
        String firstLang = "";
        String secondLang = "";
        TreeMap temp = null;
        String translatedPhrase = "";
        //Creats an array of strings which is the size of the text file passed in
        if(textFile != null){
        String[] translatedFile = new String[textFile.length];
        String[] translations = new String[5];
        long startTime = System.currentTimeMillis();
        long finishTime = 0;
        long executionTime = 0;

        int userChoiceFirst = 0;
        System.out.println("Please select the first language to display");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    temp = this.dictionariesEnglishTo;
                    firstLang = "English";
                    break;
                case 2:
                    temp = this.dictionariesGermanTo;
                    firstLang = "German";
                    break;
                case 3:
                    temp = this.dictionariesFrenchTo;
                    firstLang = "French";
                    break;
                case 4:
                    temp = this.dictionariesSpanishTo;
                    firstLang = "Spanish";
                    break;
                case 5:
                    temp = this.dictionariesItalianTo;
                    firstLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 1 || userChoiceFirst > 5);

        System.out.println("Please select your choice for the language to translate to");
        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");

        int userChoice = 0;
        do {
            userChoiceFirst = Genio.getInteger();
            switch (userChoiceFirst) {
                case 1:
                    userChoice = 0;
                    secondLang = "English";
                    break;
                case 2:
                    userChoice = 1;
                    secondLang = "German";
                    break;
                case 3:
                    userChoice = 2;
                    secondLang = "French";
                    break;
                case 4:
                    userChoice = 3;
                    secondLang = "Spanish";
                    break;
                case 5:
                    userChoice = 4;
                    secondLang = "Italian";
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        } while (userChoiceFirst < 0 || userChoiceFirst > 5);

        for (int i = 0; i < textFile.length; i++) {

            //Removes all punctuation, turns it into lower case and splits the text file
            String[] phrase = textFile[i].replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");

            if (userChoice == 0) {
                System.out.println("");
                secondLang = "English";
                for (int j = 0; j < phrase.length; j++) {
                    translations = (String[]) temp.get(phrase[j]);

                    if (translations == null || translations[0] == " ") {
                        translatedPhrase = translatedPhrase + phrase[j] + " ";

                    } else {
                        translatedPhrase = translatedPhrase + translations[0] + " ";

                    }
//Capitalises the phrase
                    translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

                }
            } else if (userChoice == 1) {
                System.out.println("");
                secondLang = "German";
                for (int j = 0; j < phrase.length; j++) {
                    translations = (String[]) temp.get(phrase[j]);

                    if (translations == null || translations[1] == " ") {
                        translatedPhrase = translatedPhrase + phrase[j] + " ";

                    } else {
                        translatedPhrase = translatedPhrase + translations[1] + " ";

                    }

                    translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

                }
            } else if (userChoice == 2) {
                System.out.println("");
                secondLang = "French";
                for (int j = 0; j < phrase.length; j++) {
                    translations = (String[]) temp.get(phrase[j]);

                    if (translations == null || translations[2] == " ") {
                        translatedPhrase = translatedPhrase + phrase[j] + " ";

                    } else {
                        translatedPhrase = translatedPhrase + translations[2] + " ";

                    }

                    translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

                }
            } else if (userChoice == 3) {
                System.out.println("");
                for (int j = 0; j < phrase.length; j++) {
                    translations = (String[]) temp.get(phrase[j]);

                    if (translations[3] == null || translations[3] == " ") {
                        translatedPhrase = translatedPhrase + phrase[j] + " ";

                    } else {
                        translatedPhrase = translatedPhrase + translations[3] + " ";

                    }

                    translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

                }
            } else if (userChoice == 4) {
                System.out.println("");
                for (int j = 0; j < phrase.length; j++) {
                    translations = (String[]) temp.get(phrase[j]);

                    if (translations == null || translations[4] == " ") {
                        translatedPhrase = translatedPhrase + phrase[j] + " ";

                    } else {
                        translatedPhrase = translatedPhrase + translations[4] + " ";

                    }

                    translatedPhrase = Character.toUpperCase(translatedPhrase.charAt(0)) + translatedPhrase.substring(1);

                }
            }
            translatedFile[i] = translatedPhrase;
        }

        finishTime = System.currentTimeMillis();
        executionTime = finishTime - startTime;

        //Prints the full text file by looping through the original array
        System.out.println(firstLang);
        for (int b = 0; b < textFile.length; b++) {
            System.out.println(textFile[b]);
        }
        //Prints the full text file by looping through the translated array
        System.out.println(secondLang);
        for (int c = 0; c < translatedFile.length; c++) {
            System.out.println(translatedFile[c]);
        }
        System.out.println("This translation took: " + executionTime + " milli seconds");
    }
    }

    /**
     * Open Text File Method
     *
     * This method opens up the external text file which needs to be translated
     *
     * @return
     */
    public String[] openTextFile() {
        try {
            System.out.println("What is the name of the text file you wish to open (In the form nemoffile.txt)");
            String fileName = Genio.getString();

            BufferedReader textFile = new BufferedReader(new FileReader(fileName));

            String textFileSentence;
            //Creates an array list to contain all the sentences of the text file
            List<String> listOfSentences = new ArrayList<String>();
            while ((textFileSentence = textFile.readLine()) != null) {
                listOfSentences.add(textFileSentence);
            }

            textFile.close();
            //Converts the array list to a normal array and returns the array
            String[] textFileArray = listOfSentences.toArray(new String[listOfSentences.size()]);
            return textFileArray;

            //Tells the user if there was an error with either reading the file or finding it
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        } catch (IOException e) {
            System.out.println("There has been a reading error");
        }
        return null;
    }

    /**
     * Add To Dictionary Method
     *
     * This method allows the user to add to the dictionary if they choose.
     */
    public void addToDictionary() {
        TreeMap dictionary = null;

        System.out.println("What language would you like to add a word to?");

        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");
        int userChoice = Genio.getInteger();
        do {

            switch (userChoice) {
                case 1:
                    dictionary = this.dictionariesEnglishTo;
                    processAdd(dictionary);
                    break;
                case 2:
                    dictionary = this.dictionariesGermanTo;
                    processAdd(dictionary);
                    break;
                case 3:
                    dictionary = this.dictionariesFrenchTo;
                    processAdd(dictionary);
                    break;
                case 4:
                    dictionary = this.dictionariesSpanishTo;
                    processAdd(dictionary);
                    break;
                case 5:
                    dictionary = this.dictionariesItalianTo;
                    processAdd(dictionary);
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }

        } while (userChoice < 1 || userChoice > 5);

        System.out.println("Would you like to save the dictionary now? 1 for yes, any other key for no");
        int choice = Genio.getInteger();

        if (choice == 1) {
            this.saveDictionary();
        }
    }

    /**
     * Process Add Method
     *
     * Processes the users add to the dictionary. The user is asked to enter for
     * each language, however if they do not know the translation they can leave
     * it blank
     *
     * @param dictionary
     */
    public void processAdd(TreeMap dictionary) {
        String[] translations = new String[5];
        System.out.println("Please enter the word to add to the dictionary you chose");
        String word = Genio.getString();
        System.out.println("Please enter the translations (If it asks for the language you have already entered, please enter it again) ");
        System.out.println("If you don't the translation, please leave the space blank");
        System.out.println("English");
        translations[0] = Genio.getString();
        System.out.println("German");
        translations[1] = Genio.getString();
        System.out.println("French");
        translations[2] = Genio.getString();
        System.out.println("Spanish");
        translations[3] = Genio.getString();
        System.out.println("Italian");
        translations[4] = Genio.getString();

        //Places the words in the tree map
        dictionary.put(word, translations);
    }

    /**
     * Save Dictionary Method
     *
     * This method is used to save the dictionaries contents.
     *
     */
    public void saveDictionary() {
        try {
            String[] translations = new String[5];
            //Opens up a print writer for each dictionary
            PrintWriter english = new PrintWriter("endictionary.txt");
            PrintWriter german = new PrintWriter("dedictionary.txt");
            PrintWriter french = new PrintWriter("frdictionary.txt");
            PrintWriter spanish = new PrintWriter("esdictionary.txt");
            PrintWriter italian = new PrintWriter("itdictionary.txt");

            //Creats a set and an iterator for the dictionary
            Set set = dictionariesEnglishTo.entrySet();
            Iterator itr = set.iterator();

            //Iterates through and adds the words to each dictionary
            while (itr.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) itr.next();
                translations = (String[]) mapEntry.getValue();
                english.println(translations[0]);
                german.println(translations[1]);
                french.println(translations[2]);
                spanish.println(translations[3]);
                italian.println(translations[4]);
            }

            english.close();
            german.close();
            french.close();
            spanish.close();
            italian.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        } catch (IOException e) {
            System.out.println("There has been a writing error");
        }
    }

    /**
     * Remove Words Method
     *
     * This method allows the user to remove words from the dictionary and save
     * the dictionary after doing so.
     */
    public void removeWords() {
        TreeMap dictionary = null;

        System.out.println("What language would you like to delete a word from?");

        System.out.println("1) English");
        System.out.println("2) German");
        System.out.println("3) French");
        System.out.println("4) Spanish");
        System.out.println("5) Italian");
        int userChoice = Genio.getInteger();
        do {

            switch (userChoice) {
                case 1:
                    dictionary = this.dictionariesEnglishTo;

                    break;
                case 2:
                    dictionary = this.dictionariesGermanTo;

                    break;
                case 3:
                    dictionary = this.dictionariesFrenchTo;

                    break;
                case 4:
                    dictionary = this.dictionariesSpanishTo;

                    break;
                case 5:
                    dictionary = this.dictionariesItalianTo;

                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }

        } while (userChoice < 1 || userChoice > 5);

        System.out.println("Please enter the word you would like to delete");
        String wordToDelete = Genio.getString();

        //Removes the key and the values realted to the key from the tree map
        dictionary.remove(wordToDelete);

        System.out.println("Would you like to save the dictionary now? 1 for yes, any other key for no");
        int choice = Genio.getInteger();

        if (choice == 1) {
            this.saveDictionary();
        }
    }

}
