package se.nackademin.planet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Element {
    private String elementsName;

    public void setElementsName(String name) {
        this.elementsName = name;
    }

    public String gietElementsName() {
        return this.elementsName;
    }

    public String[] giveOnePossipleSymbolFromSuggestionList(/*String elementsName, */int number) throws FileNotFoundException, IOException {
        //Assignment #1
        //Take symbol number "number" from suggestion list
        //Here we mean that file "suggestionList.txt" was build before
        String[] resultSymbol = new String[2];
        String suggestion;
        int i = 0;
        BufferedReader in = new BufferedReader(new FileReader("suggestionList.txt"));
        while (((suggestion = in.readLine()) != null) & (i < number)) {
            i++;
        }
        resultSymbol[0] = elementsName;
        resultSymbol[1] = suggestion;
        String arrayToString = Arrays.toString(resultSymbol);
        System.out.println("This is element number " + number + " from suggestion list: " + arrayToString);
        return resultSymbol;

    }

    public int prepareSuggestionList(/*String elementsName*/) throws FileNotFoundException, IOException {
        //Assignment #2 
        //Build a list with all possible suggestion symbols and write it to file "elements.txt"
        //Return like result number of elements in this list
        int size = elementsName.length();
        elementsName = elementsName.toUpperCase();
        boolean flag = false;
        int numberOfDistinctValidSymbols = 0;
        String symbol, suggestion;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                flag = false;
                symbol = "" + elementsName.charAt(i) + elementsName.charAt(j);
                BufferedReader in = new BufferedReader(new FileReader("suggestionList.txt"));
                while (((suggestion = in.readLine()) != null) & (flag == false)) //Check if the symbol was already presented in suggesting list
                {
                    if (suggestion.equals(symbol)) {
                        //        System.out.println(suggestion+"already present");
                        flag = true;
                    }
                }
                if (flag == false) {
                    numberOfDistinctValidSymbols++;
                    FileWriter file = new FileWriter("suggestionList.txt", true);
                    file.write(symbol + '\n');
                    file.flush();
                }

            }
        }
        //read ready txt-file and print out all suggesting list
        BufferedReader result = new BufferedReader(new FileReader("suggestionList.txt"));
        while ((suggestion = result.readLine()) != null) {
            System.out.println(suggestion);
        }
        //    System.out.println(numberOfDistinctValidSymbols);
        return numberOfDistinctValidSymbols;
    }

    private boolean checkTheSymbol(/*String elementsName,*/ String suggestion) throws IOException {
        //Check that given symbol are right or false for given name of element
        //Here we mean that file "suggestionList.txt" was build before
        boolean flag = false;
        String currentSymbol;
        suggestion=suggestion.toUpperCase();
        BufferedReader in = new BufferedReader(new FileReader("suggestionList.txt"));
        while (((currentSymbol = in.readLine()) != null) & (flag == false)) {
            if (currentSymbol.equals(suggestion)) {
                //System.out.println(suggestion+"was correct");
                flag = true;
            }
        }
        return flag;
    }
    
    public void checkAndWriteTheSymbolsList(String[][] listOfElementsWithAbbreviation) throws FileNotFoundException, IOException {
        //Assignment #4
        String currentElement;
        int tmp;
        Writer file = null;
        Writer file1 = null;

        //build suggestion list for each element and currently write it in suggestionList.txt
        //make checking for each pair (element, abriviation)
        //write the result of this checking to file resulListOfElementsWithAbbreviation.txt
        int size = listOfElementsWithAbbreviation.length;
        boolean currentFlag = false;
        try{
            file1 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("resulListOfElementsWithAbbreviation.txt")));

            for (int i = 0; i < size; i++) {
            try{
                file = new PrintWriter(new OutputStreamWriter(new FileOutputStream("suggestionList.txt")));
                this.setElementsName(listOfElementsWithAbbreviation[i][0]);
                tmp = prepareSuggestionList();
                currentFlag = checkTheSymbol(listOfElementsWithAbbreviation[i][1]);
                file1.write(listOfElementsWithAbbreviation[i][0]+ ",  "+listOfElementsWithAbbreviation[i][1]+"->" + currentFlag+'\n');
                file1.flush();

                }finally{
                  if(file != null){
                     file.close();
                  }
                }
            
            }
        }finally{
              if(file1 != null){
                 file1.close();
              }
        
        } 
        
    }

}
