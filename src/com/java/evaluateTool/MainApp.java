package com.java.evaluateTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {

  static class Utils {

    static Double averageMinute(List<ItemC> objectList) {
       return objectList.stream()
              .mapToInt(e -> Integer.parseInt(e.getMinutes()))
              .average().getAsDouble();
    }

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
    List<String> arrayC = new ArrayList<>();
    List<String> arrayD = new ArrayList<>();
    Map<String, String> filteredMap;


    /*try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

      while (i < n) {

        System.out.print("Enter something : ");
        String input = br.readLine();

        *//*if (!Utils.validateQuery(input)) {
          break;
        }*//*

        if (Utils.isInt(input)) {
          n = Integer.parseInt(input);
          i++;
        } else {
          if (input.charAt(0) == 'C') {
            arrayC.add(input);
          } else if (input.charAt(0) == 'D') {
            arrayD.add(input);
          }

          //create and store 2 arraylist 1 for c 1 for d

          //create stream from list c

          //for loop arraylist from d with filter and predicate

          //create final hashmap key d value list c

          //queryStorage.addQuery(i, input);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
*/
    String test = "C 1.1 8.15.1 P 15.10.2012 83";
    String testC1 = "C 1 10.1 P 01.12.2012 65";
    String testC2 = "C 1.1 5.5.1 P 01.11.2012 117";
    String testD = "D 1.1 8 P 01.01.2012-01.12.2012";
    String testC3 = "C 3 10.2 N 02.10.2012 100";
    String testD2 = "D 1 * P 8.10.2012-20.11.2012";
    String testD3 = "D 3 10 P 01.12.2012";


    arrayC.add(test);
    arrayC.add(testC1);
    arrayC.add(testC2);
    arrayC.add(testC3);

    arrayD.add(testD);
    arrayD.add(testD2);
    arrayD.add(testD3);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    List<ItemC> itemCList = new ArrayList<>();
    List<ItemD> itemDList = new ArrayList<>();


    arrayC.forEach(e -> {
     String[] temp = e.split(" ");
      itemCList.add(new ItemC(temp[1], temp[2], temp[3], LocalDate.parse(temp[4], formatter), temp[5]));
    });

    arrayD.forEach(e -> {
      String[] temp = e.split(" ");
      if (!temp[4].contains("-")) {
        itemDList.add(new ItemD(temp[1], temp[2], temp[3], LocalDate.parse(temp[4], formatter)));
      } else {
        String[] temp2 = temp[4].split("-");
        itemDList.add(new ItemD(temp[1], temp[2], temp[3], LocalDate.parse(temp2[0], formatter), LocalDate.parse(temp2[1], formatter)));
      }
    });
    //String[] split = test.split(" ");
    //String[] split1 = testD.split(" ");
    /*ItemD itemD;
    if (!split1[4].contains("-")) {
      itemD = new ItemD(split1[1], split1[2], split1[3], LocalDate.parse(split1[4], formatter));
    } else {
      String[] split2 = split1[4].split("-");
      itemD = new ItemD(split1[1], split1[2], split1[3], LocalDate.parse(split2[0], formatter), LocalDate.parse(split2[1], formatter));
    }*/

    /*ItemC itemC = new ItemC(split[1], split[2], split[3], LocalDate.parse(split[4], formatter), split[5]);
    for (String s : split) {
      System.out.println(s);
    }*/
    //System.out.println(itemC.toString());
   // System.out.println(itemD.toString());

    //itemCList.add(itemC);

    //itemDList.add(itemD);

    Stream<ItemC> itemCStream = itemCList.stream();

    Map<ItemD, List<ItemC>> map = new HashMap<>();
    itemDList.forEach(d -> {
      List<ItemC> collectItemC = itemCStream.filter(e -> e.getDateFrom().isAfter(d.getDateFrom())
              && e.getDateFrom().isBefore(d.getDateTo()) || e.getDateFrom().isEqual(d.getDateFrom())
              || e.getDateFrom().isEqual(d.getDateTo()))
              .filter(e -> Integer.parseInt(String.valueOf(e.getServiceId().charAt(0)))
                      == Integer.parseInt(String.valueOf(d.getServiceId().charAt(0))) || d.getServiceId().equals("*"))
              .filter(e -> Integer.parseInt(String.valueOf(e.getQuestionType().charAt(0)))
                      == Integer.parseInt(String.valueOf(d.getQuestionType().charAt(0))) || d.getQuestionType().equals("*"))
          .collect(Collectors.toList());
      map.put(d, collectItemC);
    });

    map.forEach((k,v) ->
      System.out.println(Utils.averageMinute(v))
    );

  }

}
