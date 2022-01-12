import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.lang.String;

public class Korean_Numbers {

  private static final int SINO = 0;
  private static final int NATIVE = 1;

  // reads number file and adds key-value pairs to a hashmap
  public static void readNumberFile(String filename, HashMap<String, String> map) {
    try {
      File txtFile = new File(filename);
      Scanner scan = new Scanner(txtFile);
      while (scan.hasNextLine()) {
        String line = scan.nextLine(); //gets entire line
        // System.out.println("Line: " + line);
        
        String[] tokens = line.split("\\s+"); // any whitespace char
        
        // for (String token : tokens) System.out.println(token);
        
        //convert String to Integer (DON'T NEED IF: numToWords iterates through String-converted number)
        map.put(tokens[0], tokens[1]);

      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

  //outside code decides which number system to use
  public static String numToWords(int num, HashMap<String, String> numberMap) {
    String numStr = Integer.toString(num);

    if (numberMap.containsKey(numStr)) return numberMap.get(numStr);

    //builing the String
    String result = "";
    
    int length = numStr.length();
    //System.out.println(length);
    
    int highestPower = length-1;
    int currentPower = highestPower;

    //THINGS TO CONSIDER
    //what if the digit is 0
    //what to do when currentPower is 5, 6, or 7 (how to pronouce)
    //e.g. 123456, 223456, 100001
    //consider numbers with powers of 10 greater than 8--generalize

    for (int i = 0; i < length; i++) {

    }
    
    
    
    
    
    
    
    for (int i = 0; i < length; i++) {
      String digit = Character.toString(numStr.charAt(i));
      System.out.println(digit); //returns digit as char
      
      if (!digit.equals("0")) { //can do everything else
        //System.out.println("Digit is not 0");
      }
        
      result += numberMap.get(digit);

      //if (digit instanceof String) System.out.println("Success");
      //how to get the right word for each power (e.g. 967 and get the 100 from 9 * 100)

      if (currentPower == 8 || currentPower <=4) {
        int powerOf10 = 10 * currentPower;
        result += numberMap.get(Integer.toString(powerOf10));
      }
      
      if (currentPower >= 5 && currentPower <= 7) {
        //powers 5-7 (inclusive)
      }

        else if (currentPower == 0) { //digit is a key in the hashmap
        result += numberMap.get(digit);
        }
      }

      System.out.println("current result: " + result);
    }
    
    
    
    return result;
  }

  public static void main(String[] args) {
    System.out.println("Hello");
    System.out.println("Testing readNumberFile()");
    
    HashMap<String, String> sinoMap = new HashMap<>();
    HashMap<String, String> nativeMap = new HashMap<>();
    
    readNumberFile("sinoNumbers.txt", sinoMap);
    System.out.println(sinoMap.keySet());

    readNumberFile("nativeNumbers.txt", nativeMap);
    System.out.println(nativeMap.keySet());

    //testing numToWords
    System.out.println(numToWords(99, sinoMap));
    System.out.println(numToWords(99, nativeMap));
  }
}