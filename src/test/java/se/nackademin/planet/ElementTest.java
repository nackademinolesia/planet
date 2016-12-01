package se.nackademin.planet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import static java.lang.Character.isLetter;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ElementTest {
    
Element element;
File file = new File("suggestionList.txt");

 @Before
 public void setUp() throws IOException {
    element = new Element();
    element.setElementsName("brrrom");
    if(file.exists()){
        file.delete();
    }
    //file = new File("suggestionList.txt");
    file.createNewFile();
 }

  @Test
  public void testSuggestionNameIsCorrect() throws IOException {
      // Check that function giveOnePossipleSymbolFromSuggestionList works properly
      // It means reaturn right name of the element and right suggestion
      element.prepareSuggestionList();
      String[] result = element.giveOnePossipleSymbolFromSuggestionList(2);
      String s1=element.gietElementsName();
      String s2=element.giveOnePossipleSymbolFromSuggestionList(2)[0];
      //check that function return elements name right
      assertEquals("Name of the element ", s1, s2);
      //check that function return suggestion abbreviation with chosed number right
      assertEquals("Symbol for suggestion number " +2+" should be: ", "BM", element.giveOnePossipleSymbolFromSuggestionList(2)[1]);
  }

  @Test
  public void testPrepareSuggestionList() throws IOException {
      //Check that number of possible suggestions of abbreviation is correct
      element.setElementsName("brrrom");
      assertEquals("Number of possible suggestions isn't correct", 7, element.prepareSuggestionList());
  }
  
  @Test
  public void testUsingUpperAndLowerCaseInSuggestionList() throws IOException {
      //Check that function dosn't missunderstand that the letter in uppercase and lower case are actualy the same letter
      //So we can't suggest for the element *Mamba* abbreviation *Ma* and *ma* som different, they are the same
      element.setElementsName("MambA");
      assertEquals("Number of possible suggestions isn't correct. Possible wrong counting because uppercase", 7, element.prepareSuggestionList());
  }
  
  @Test
  public void testSuggestionHasPreciselyTwoLetters() throws IOException {
      //Check that function giveOnePossipleSymbolFromSuggestionList gives like result an abbreviation
      //which has precisly two letters
      element.setElementsName("brrrom");
      element.prepareSuggestionList();
      int tmp = element.giveOnePossipleSymbolFromSuggestionList(3)[1].length();
      assertEquals("Suggested abbreviation should contain precisly two letters", 2, tmp);
  }

  @Test
  public void testSuggestionIncludeOnlyLetters() throws IOException {
      //Check that in suggestion abbreviation can be include only letters
      boolean flag1 = true;
      boolean flag2 = true;
      boolean flag = true;
      char ch1, ch2;
      element.setElementsName("brrrom");
      int size = element.prepareSuggestionList();
      String[] tmp;
      int i=0;
      //going throw whole list of suggestions
      //change a flag to false if one of char in suggested
      //abbreviation is not a leeter
      while((i<size)&(flag1==true)&(flag2==true)){
          tmp = element.giveOnePossipleSymbolFromSuggestionList(i);
          flag1 = isLetter(tmp[1].charAt(0));
          flag2 = isLetter(tmp[1].charAt(1));
          i++;
      }
      if ((flag1==false)||(flag2==false)) flag = false;
      assertEquals("Suggested abbreviation should contain ONLY letters, but it connected something else", true, flag);
  }
 
}
