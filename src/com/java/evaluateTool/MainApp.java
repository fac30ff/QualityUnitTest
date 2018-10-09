package com.java.evaluateTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {

  static class Utils {





    static boolean checkIfFirstLineIsNumber(int number) {
      return (Number)number instanceof Number;
    }

    static boolean validateQuery(String input) {
      String s1 = ;
      String s2 = "(000[0-9] | 9999)";


      return (Number)Integer.parseInt(input) instanceof Number || input
    }
  }

  public static void main(String[] args) {

    BufferedReader br = null;
    Integer n = 1;
    int i = 0;
    QueryStorage queryStorage = new QueryStorage();


    try {

      br = new BufferedReader(new InputStreamReader(System.in));

      while (i < n) {

        System.out.print("Enter something : ");
        String input = br.readLine();

        if (input.length() == 1 && Utils.checkIfFirstLineIsNumber(Integer.parseInt(input))) {
          n = Integer.parseInt(input);
          i++;
        } else {
          queryStorage.addQuery(i, input);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }



  }
}
