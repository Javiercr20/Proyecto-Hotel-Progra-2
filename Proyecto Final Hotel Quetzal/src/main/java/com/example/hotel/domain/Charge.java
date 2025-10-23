package com.example.hotel.domain;
import java.math.BigDecimal; import java.time.LocalDate;
public class Charge {
  private String description; private BigDecimal amount; private LocalDate date; private String category;
  public Charge() {}
  public Charge(String desc, BigDecimal amount, LocalDate date, String category){ this.description=desc; this.amount=amount; this.date=date; this.category=category; }
  public String getDescription(){return description;} public void setDescription(String v){this.description=v;}
  public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){this.amount=v;}
  public LocalDate getDate(){return date;} public void setDate(LocalDate v){this.date=v;}
  public String getCategory(){return category;} public void setCategory(String v){this.category=v;}
}
