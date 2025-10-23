package com.example.hotel.domain;
import java.math.BigDecimal; import java.time.LocalDate;
public class Payment {
  private BigDecimal amount; private LocalDate date; private String method; private String reference;
  public Payment() {}
  public Payment(BigDecimal amount, LocalDate date, String method, String reference){ this.amount=amount; this.date=date; this.method=method; this.reference=reference; }
  public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){this.amount=v;}
  public LocalDate getDate(){return date;} public void setDate(LocalDate v){this.date=v;}
  public String getMethod(){return method;} public void setMethod(String v){this.method=v;}
  public String getReference(){return reference;} public void setReference(String v){this.reference=v;}
}
