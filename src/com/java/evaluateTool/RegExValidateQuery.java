package com.java.evaluateTool;

public enum RegExValidateQuery {
  WAITING_TIME_LINE_OR_QUERY("(C|D)"), SERVICE_AND_VARIATION_IDS("(\\d+(\\.\\d+)*)"),
  QUESTION_TYPE_CATEGORY_SUB_IDS("(\\d+(\\.\\d+)*)"), FIRST_NEXT_ANSWER("(P|N)"),
  DATE_FROM_TO("([0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2})((-([0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}))?)"),
  IIME("((\\s\\d+)?)"),
  FULL_REGEX("^(C|D)\\s((\\d+(\\.\\d+)*)|\\*)\\s((\\d+(\\.\\d+)*)|\\*)\\s(P|N)\\s([0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2})((-([0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}))?)((\\s\\d+)?)");

  private final String name;

  private RegExValidateQuery(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }

}
