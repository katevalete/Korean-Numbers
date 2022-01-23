import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.lang.String;

import java.math.BigDecimal;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

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

  public static String numToSino(long num, HashMap<String, String> numberMap) {
    //System.out.println("converting the number " + num);
    String numStr = Long.toString(num);

    //builing the String
    String result = "";
    
    int length = numStr.length();
    //System.out.println(length);
    
    int highestPower = length-1;
    int currentPower = highestPower;

    //System.out.println("highest power: " + highestPower);

    //if the number is a power of 10 or is a key in the numberMap
    if (numberMap.containsKey(numStr)) {
      if (highestPower == 8 || highestPower == 12) {
        return "일" + numberMap.get(numStr);
      }
      return numberMap.get(numStr);
    }

    int index = 0;
    //iterate through numStr
    while (index < length) {
      //System.out.println("current i: " + index);
      //get power of 10
      long powerOf10 = (long) Math.pow(10, currentPower);
      //convert to int
      //System.out.println("powerOf10: " + powerOf10);

      String pow10Str = Long.toString(powerOf10);
      String pow10Key = numberMap.get(pow10Str); //should be value

      if (pow10Key.length() == 2) { //if the powerOf10's value is of length 2
        //System.out.println("power of 10 key: " + pow10Key);

        //get second syllable
        String syllable2 = Character.toString(pow10Key.charAt(1));

        //System.out.println("second syllable: " + syllable2);

        if (currentPower < 8) {
          //System.out.println("current power < 8");
          //divide by 10^4
          long numCopy = Long.parseLong(numStr.substring(index, length));
          //System.out.println("number copy: " + numCopy);
          numCopy /= 10000;
          //System.out.println("substring: " + numCopy);

          //how to get the appropriate length?
          int subStrLength = (Long.toString(numCopy)).length();
          //System.out.println("substring length: " + subStrLength);
          
          //call numToWords for the SUBSTRING
          result += numToSino(numCopy, numberMap) + "만 ";

          //update index
          index += subStrLength;
          //System.out.println("new index: " + index);
          currentPower -= subStrLength;
          //System.out.println("new current power: " + currentPower);
        }

        else if (currentPower > 8) {
          //System.out.println("current power > 8");
          //divide by 10^8
          long numCopy = Long.parseLong(numStr.substring(index, length));
          //System.out.println("number copy: " + numCopy);
          numCopy /= 100000000;
          //System.out.println("substring: " + numCopy);

          //how to get the appropriate length?
          int subStrLength = (Long.toString(numCopy)).length();
          //System.out.println("substring length: " + subStrLength);
          
          //call numToWords for numCopy
          result += numToSino(numCopy, numberMap) + "억 ";

          //update index
          index += subStrLength;
          //System.out.println("new index: " + index);

          //LOOKING AT NEW NUMBER NOW (different power of 10)
          currentPower -= subStrLength;
          //System.out.println("new current power: " + currentPower);
        }
      }

      else { //powerOf10's length is 1
        //System.out.println("powerOf10's length is 1");
        //System.out.println("pow10Key: " + pow10Key);
        //get digit in numStr
        String digit = Character.toString(numStr.charAt(index));
        //System.out.println(digit); //returns digit as char
        //need substring
        long numCopy = Long.parseLong(numStr.substring(index, length));
        //System.out.println("number copy: " + numCopy);
        
        //System.out.println("current power: " + currentPower);

        //if highest power and digit is 1 for 10^12 and 10^8
        if (currentPower == highestPower && (highestPower == 8 || highestPower == 12) && digit.equals("1")) {
          result += "일" + pow10Key + " ";
        }
        
        else {
          if (!digit.equals("0")) {
            if (currentPower == 0) result += numberMap.get(digit);
            else {
              if (!digit.equals("1")) result += numberMap.get(digit);
              result += pow10Key;
            }
          }
        }
        
        index++;
        currentPower--;
      }

      //System.out.println("current result: " + result);
      //System.out.println();

    }
    return result;
  }

  public static String numToNative(long num, HashMap<String, String> numberMap) {
    //System.out.println("converting the number " + num);
    String numStr = Long.toString(num);

    if (numberMap.containsKey(numStr)) {
      return numberMap.get(numStr);
    }

    String result = "";
    int length = numStr.length();

    if (length == 2) { //find the key of digit * 10
      String digit = Character.toString(numStr.charAt(0));
      int pow10Key = Integer.parseInt(digit) * 10;
      result += numberMap.get(Integer.toString(pow10Key));
      //System.out.println("current result: " + result);

      String onesPlace = Character.toString(numStr.charAt(1));
      result += numberMap.get(onesPlace);
    }

    return result;
  }

  //outside code decides which number system to use
  public static String numToWords(long num, HashMap<String, String> numberMap, int type) {
    if (type == SINO) {
      return numToSino(num, numberMap);
    }
    else { // Native-Korean number
      return numToNative(num, numberMap);
    }
  }

  public static boolean isInteger(String input) {
    try {
      int value = Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isLong(String input) {
    try {
      Long value = Long.parseLong(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  //checks valid user input for a range
  //entering MAX means 10^13 is the max number
  public static long getMax(Scanner scan) {
    //how to check if an input is an Integer or a Long??
    String input = scan.next();
    //System.out.println("input: " + input);
    
    while (!input.strip().equals("MAX") && !isInteger(input) && !isLong(input)) {
      System.out.println("Invalid input. Try again");
      System.out.println("Enter the maximum value for Sino-Korean numbers: ");
      input = scan.next();
    }
    
    if (input.strip().equals("MAX")) {
      return 10000000000000L;
    }
    else { //see if you can parseInt or parseLong
      if (isInteger(input)) {
        return (long) Integer.parseInt(input);
      }
      return Long.parseLong(input);
    }
    //if (scan.hasNextLong()) System.out.println("valid input");
  }

  public static void main(String[] args) {
    //System.out.println("Hello");
    //System.out.println("Testing readNumberFile()");
    
    HashMap<String, String> sinoMap = new HashMap<>();
    HashMap<String, String> nativeMap = new HashMap<>();
    
    readNumberFile("sinoNumbers.txt", sinoMap);
    //System.out.println(sinoMap.keySet());

    readNumberFile("nativeNumbers.txt", nativeMap);
    //System.out.println(nativeMap.keySet());

    //randomly choose between SINO and NATIVE (or let user decide)
    Random rand = new Random();
    int type = rand.nextInt(2);
    //System.out.println("type: " + type);

    //let user set the maximum value for Sino-Korean numbers
    System.out.println("Enter the maximum value for Sino-Korean numbers: ");
    Scanner scan = new Scanner(System.in);
    //System.out.println("user input: " + input);
    Long max = getMax(scan);
    System.out.println("Max: " + max);

    Long randNum;
    String translation;
    if (type == SINO) {
      System.out.println("Sino-Korean number: ");
      //let user enter range??
      randNum = ThreadLocalRandom.current().nextLong(0, max); 
      System.out.println(randNum);
      translation = numToWords(randNum, sinoMap, SINO);
    }
    else {
      System.out.println("Native-Korean number: ");
      randNum = (long) rand.nextInt(100);
      System.out.println(randNum);
      translation = numToWords(randNum, nativeMap, NATIVE);
    }
  }
}