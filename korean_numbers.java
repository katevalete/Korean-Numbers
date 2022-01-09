import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.lang.String;

public class Korean_Numbers {

  // reads number file and adds key-value pairs to a hashmap
  public static void readNumberFile(String filename, HashMap<Integer, String> map) {
    try {
      File txtFile = new File(filename);
      Scanner scan = new Scanner(txtFile);
      while (scan.hasNextLine()) {
        String line = scan.nextLine(); //gets entire line
        // System.out.println("Line: " + line);
        
        String[] tokens = line.split("\\s+"); // any whitespace char
        
        // for (String token : tokens) System.out.println(token);
        
        //convert String to Integer
        map.put(Integer.parseInt(tokens[0]), tokens[1]);

      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    System.out.println("Hello");
    System.out.println("Testing readNumberFile()");
    
    HashMap<Integer, String> sinoMap = new HashMap<>();
    HashMap<Integer, String> nativeMap = new HashMap<>();
    
    readNumberFile("sinoNumbers.txt", sinoMap);
    System.out.println(sinoMap.keySet());

    readNumberFile("nativeNumbers.txt", nativeMap);
    System.out.println(nativeMap.keySet());
  }
}