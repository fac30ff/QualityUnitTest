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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {

  static class Utils {

    static Double averageMinute(List<ItemC> objectList) {
       return objectList.stream()
              .mapToInt(e -> Integer.parseInt(e.getMinutes()))
              .average().getAsDouble();
    }

    static boolean validateQueryWithWaitT(String s1, String s2){
      if (s2.equals("*")) {
        return true;
      } else {
        Pattern pattern = Pattern.compile(s2 + "+");
        Matcher matcher = pattern.matcher(s1);
        return matcher.find();
      }
    }

    static boolean validateQueryWithWaitTByTime(LocalDate wt1, LocalDate q1) {
      return wt1.isAfter(q1) || wt1.isEqual(q1);
    }

    static boolean validateQueryWithWaitTByTime(LocalDate wt1, LocalDate q1, LocalDate q2) {
      return wt1.isAfter(q1) && wt1.isBefore(q2) || wt1.isEqual(q1) || wt1.isEqual(q2);
    }

    static List<ItemC> createListOfItemsC(List<String> arrayC) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
      return arrayC.stream().map(e -> e.split(" "))
              .map(temp -> new ItemC(temp[1], temp[2], temp[3], LocalDate.parse(temp[4], formatter), temp[5]))
              .collect(Collectors.toList());
    }

    static List<ItemD> createListOfItemsD(List<String> arrayD) {
      List<ItemD> itemDList = new ArrayList<>();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
      arrayD.forEach(e -> {
        String[] temp = e.split(" ");
        if (temp.length == 4 && !temp[3].contains("-")) {
          itemDList.add(new ItemD(temp[1], temp[2], LocalDate.parse(temp[3], formatter)));
        } else if(temp.length == 4 && temp[3].contains("-")) {
          String[] temp3 = temp[3].split("-");
          itemDList.add(new ItemD(temp[1], temp[2], LocalDate.parse(temp3[0], formatter), LocalDate.parse(temp3[1], formatter)));
        } else {
          if (!temp[4].contains("-")) {
            itemDList.add(new ItemD(temp[1], temp[2], temp[3], LocalDate.parse(temp[4], formatter)));
          } else {
            String[] temp2 = temp[4].split("-");
            itemDList.add(new ItemD(temp[1], temp[2], temp[3], LocalDate.parse(temp2[0], formatter), LocalDate.parse(temp2[1], formatter)));
          }
        }
      });
      return itemDList;
    }

    static Map<ItemD, List<ItemC>> mappingItemCOnItemD(List<ItemD> itemDList, List<ItemC> itemCList){
      Map<ItemD, List<ItemC>> map = new HashMap<>();
      itemDList.forEach(d -> {
        Stream<ItemC> itemCStream = itemCList.stream();
        List<ItemC> collectItemC = itemCStream.filter(e -> {
          if(d.getDateTo() == null) {
            return validateQueryWithWaitTByTime(e.getDateFrom(), d.getDateFrom());
          } else {
            return validateQueryWithWaitTByTime(e.getDateFrom(), d.getDateFrom(), d.getDateTo());
          }
        })
                .filter(e -> validateQueryWithWaitT(e.getServiceId(), d.getServiceId()))
                .filter(e -> Objects.nonNull(e.getQuestionType()))
                .filter(e -> validateQueryWithWaitT(e.getQuestionType(), d.getQuestionType()))
                .filter(e -> e.getAnswerType().equals(d.getAnswerType()))
                .collect(Collectors.toList());
        map.put(d, collectItemC);
      });
      return map;
    }

    static void printFromResultMap(Map<ItemD, List<ItemC>> map) {
      map.forEach((k,v) -> {
                if (v.size() == 0) {
                  System.out.println("-");
                } else {
                  System.out.println(averageMinute(v));
                }
              }
      );
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

    int n = 1;
    int i = 0;

    List<String> arrayC = new ArrayList<>();
    List<String> arrayD = new ArrayList<>();


    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

      while (i <= n) {
        String input = br.readLine();
        if (Utils.isInt(input)) {
          n = Integer.parseInt(input);
          i++;
        } else {
          if (input.charAt(0) == 'C') {
            arrayC.add(input);
            i++;
          } else if (input.charAt(0) == 'D') {
            arrayD.add(input);
            i++;
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

/**
//* Testing data

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
*/

    List<ItemC> itemCList = Utils.createListOfItemsC(arrayC);
    List<ItemD> itemDList = Utils.createListOfItemsD(arrayD);
    Map<ItemD, List<ItemC>> map = Utils.mappingItemCOnItemD(itemDList, itemCList);
    Utils.printFromResultMap(map);

  }

}
