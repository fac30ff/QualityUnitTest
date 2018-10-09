package com.java.evaluateTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QueryValidator {
  private static Pattern pattern;
  private Matcher matcher;

  QueryValidator(String regex) {
    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
  }

  public boolean validateQuery(String match) {
    matcher = pattern.matcher(match);
    return matcher.matches();
  }

}
