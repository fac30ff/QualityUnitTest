package com.java.evaluateTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {

  static class Utils {

    static boolean validateQuery(String input) {
      return isInt(input) || new QueryValidator(RegExValidateQuery.FULL_REGEX.name()).validateQuery(input);
    }

    static boolean isInt(String input) {
      if (input == null) {
        return false;
      }
      int length = input.length();
      if (length == 0) {
        return false;
      }
      int i = 0;
      if (input.charAt(0) == '-') {
        if (length == 1) {
          return false;
        }
        i = 1;
      }
      for (; i < length; i++) {
        char c = input.charAt(i);
        if (c <= '/' || c >= ':') {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {

    Integer n = 1;
    int i = 0;
    QueryStorage queryStorage = new QueryStorage();


    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

      while (i < n) {

        System.out.print("Enter something : ");
        String input = br.readLine();

        /*if (!Utils.validateQuery(input)) {
          break;
        }*/

        if (Utils.isInt(input)) {
          n = Integer.parseInt(input);
          i++;
        } else {

          //create and store 2 arraylist 1 for c 1 for d

          //create stream from list c

          //for loop arraylist from d with filter and predicate

          //create final hashmap key d value list c

          queryStorage.addQuery(i, input);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
