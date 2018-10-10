package com.java.evaluateTool;

import java.time.LocalDate;

class ItemC {
  private String serviceId;
  private String questionType;
  private String answerType;
  private LocalDate dateFrom;
  private String minutes;

  public ItemC(String serviceId, String questionType, String answerType, LocalDate dateFrom, String minutes) {
    this.serviceId = serviceId;
    this.questionType = questionType;
    this.answerType = answerType;
    this.dateFrom = dateFrom;
    this.minutes = minutes;
  }

  public ItemC getItemC(){
    return new ItemC(serviceId, questionType, answerType, dateFrom, minutes);
  }


  public String getServiceId() {
    return serviceId;
  }

  public String getQuestionType() {
    return questionType;
  }

  public String getAnswerType() {
    return answerType;
  }

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public String getMinutes() {
    return minutes;
  }

  @Override
  public String toString() {
    return "ItemC{" +
            "serviceId='" + serviceId + '\'' +
            ", questionType='" + questionType + '\'' +
            ", answerType='" + answerType + '\'' +
            ", dateFrom='" + dateFrom + '\'' +
            ", minutes='" + minutes + '\'' +
            '}';
  }
}
