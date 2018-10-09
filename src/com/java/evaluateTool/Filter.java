package com.java.evaluateTool;

class Filter {
  private String filterType;

  Filter(String filterType) {
    this.filterType = filterType;
  }

  public String getFilterType() {
    return filterType;
  }

  public void setFilterType(String filterType) {
    this.filterType = filterType;
  }

  @Override
  public String toString() {
    return "Item{" +
            "filterType='" + filterType + '\'' +
            '}';
  }
}
