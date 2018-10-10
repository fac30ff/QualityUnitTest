package com.java.evaluateTool;

import java.time.LocalDate;

class ItemD {
  private String serviceId;
  private String questionType;
  private String answerType;
  private LocalDate dateFrom;
  private LocalDate dateTo;

  ItemD(String serviceId, String answerType, LocalDate dateFrom, LocalDate dateTo) {
    this.serviceId = serviceId;
    this.answerType = answerType;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
  }
  ItemD(String serviceId, String answerType, LocalDate dateFrom) {
    this.serviceId = serviceId;
    this.answerType = answerType;
    this.dateFrom = dateFrom;
  }

  ItemD(String serviceId, String questionType, String answerType, LocalDate dateFrom) {
    this.serviceId = serviceId;
    this.questionType = questionType;
    this.answerType = answerType;
    this.dateFrom = dateFrom;
  }

  ItemD(String serviceId, String questionType, String answerType, LocalDate dateFrom, LocalDate dateTo) {
    this.serviceId = serviceId;
    this.questionType = questionType;
    this.answerType = answerType;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
  }

  public ItemD getItemD(){
    return new ItemD(serviceId, questionType, answerType, dateFrom, dateTo);
  }

  String getServiceId() {
    return serviceId;
  }

  String getQuestionType() {
    return questionType;
  }

  String getAnswerType() {
    return answerType;
  }

  LocalDate getDateFrom() {
    return dateFrom;
  }

  LocalDate getDateTo() {
    return dateTo;
  }

  @Override
  public String toString() {
    return "ItemD{" +
            "serviceId='" + serviceId + '\'' +
            ", questionType='" + questionType + '\'' +
            ", answerType='" + answerType + '\'' +
            ", dateFrom=" + dateFrom +
            ", dateTo=" + dateTo +
            '}';
  }
}
