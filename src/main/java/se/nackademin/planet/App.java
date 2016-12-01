package se.nackademin.planet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class App {
    

  public static void main(String[] args) throws IOException {
    System.out.println("Hello World! I'm the best!!!");
    Writer file = null;
    int numberOfDistinctValidSymbols = 0;
    boolean flag=false;
    Element element = new Element();
//    String nameOfElementForChecking = "Olesia";
    element.setElementsName("Olesia");
 
    String listOfElementsWithAbbreviation [][] ={{"Hydrogen", "Hy"},{"Helium","Lu"}, {"Lithium","Ii"}, {"Beryllium","Le"}, {"Boron","Rr"}, {"Carbon","Cf"}};
    try{
        file = new PrintWriter(new OutputStreamWriter(new FileOutputStream("suggestionList.txt")));
        numberOfDistinctValidSymbols = element.prepareSuggestionList();
        String[] resultSymbol = element.giveOnePossipleSymbolFromSuggestionList(0);
        String arrayToString = Arrays.toString(resultSymbol);
        System.out.println("This is our best suggestion: " + arrayToString);
//        flag=element.checkTheSymbol("Olesia", "bb");
//        System.out.println("Looks like suggestion is: " + flag);
        }finally{
          if(file != null){
             file.close();
          }
    }
   element.checkAndWriteTheSymbolsList(listOfElementsWithAbbreviation);
  }

}
