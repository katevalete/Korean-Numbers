import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.lang.String;

import java.math.BigDecimal;

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
  public static String numToWords(long num, HashMap<String, String> numberMap) {
    String numStr = Long.toString(num);

    //builing the String
    String result = "";
    
    int length = numStr.length();
    //System.out.println(length);
    
    int highestPower = length-1;
    int currentPower = highestPower;

    System.out.println("highest power: " + highestPower);

    //if the number is a power of 10 or is a key in the numberMap
    if (numberMap.containsKey(numStr)) {
      if (highestPower == 8 || highestPower == 12) {
        return "일" + numberMap.get(numStr);
      }
      return numberMap.get(numStr);
    }

    //iterate through numStr
    for (int i = 0; i < length; i++) {
      //get power of 10
      long powerOf10 = (long) Math.pow(10, currentPower);
      //convert to int
      System.out.println("powerOf10: " + powerOf10);

      String pow10Str = Long.toString(powerOf10);
      String pow10Key = numberMap.get(pow10Str);

      if (pow10Key.length() == 2) { //if the powerOf10's value is of length 2
        System.out.println(pow10Key);

        //get second syllable
        String syllable2 = Character.toString(pow10Key.charAt(1));

        System.out.println(syllable2);

        if (currentPower < 8) {
          //divide by 10^4
          
          //get the number
        }

        else if (currentPower == 9) {
          //divide by 10^8
        }
      }

      //get digit
      String digit = Character.toString(numStr.charAt(i));
      //System.out.println(digit); //returns digit as char

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
    //System.out.println(numToWords(99, nativeMap));

    /*
    System.out.println(numToWords(1, sinoMap));
    System.out.println(numToWords(10, sinoMap));
    System.out.println(numToWords(100, sinoMap));
    System.out.println(numToWords(1000, sinoMap));
    System.out.println(numToWords(10000, sinoMap));
    System.out.println(numToWords(100000, sinoMap));
    */

    /*
    System.out.println(numToWords(1000000, sinoMap));
    System.out.println(numToWords(10000000, sinoMap));
    System.out.println(numToWords(100000000, sinoMap));
    System.out.println(numToWords(1000000000, sinoMap));
    System.out.println(numToWords(10000000000L, sinoMap));
    System.out.println(numToWords(100000000000L, sinoMap));
    System.out.println(numToWords(1000000000000L, sinoMap));
    */
    System.out.println(numToWords(120000, sinoMap));
    System.out.println(numToWords(1200000, sinoMap));
    System.out.println(numToWords(12000000, sinoMap));
  }
}