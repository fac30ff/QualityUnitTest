package com.java.evaluateTool;

import java.util.HashMap;
import java.util.Map;

class QueryStorage {
  private Map<Integer, String> storageMap;

  QueryStorage(){
    this.storageMap = new HashMap<>();
  }

  QueryStorage(Map<Integer, String> storageMap) {
    this.storageMap = storageMap;
  }

  public void addQuery(Integer i, String s) {
    this.storageMap.put(i, s);
  }

  public void clearQuery() {
    this.storageMap.clear();
  }

}
