package com.example.hotel.domain;
import java.math.BigDecimal; import java.time.LocalDate; import java.util.*; 
public class Reservation {
  private Long id; private String code; private Long roomId; private Long guestId;
  private LocalDate checkInDate; private LocalDate checkOutDate; private int occupants; private BigDecimal nightlyRate;
  private ReservationStatus status;
  private final java.util.List<Charge> charges = new ArrayList<>(); private final java.util.List<Payment> payments = new ArrayList<>();
  public Reservation(){}
  public Reservation(Long id, Long roomId, Long guestId, LocalDate checkInDate, LocalDate checkOutDate, int occupants, BigDecimal nightlyRate){
    this.id=id; this.code=java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();
    this.roomId=roomId; this.guestId=guestId; this.checkInDate=checkInDate; this.checkOutDate=checkOutDate; this.occupants=occupants; this.nightlyRate=nightlyRate;
    this.status=ReservationStatus.RESERVADA;
  }
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getCode(){return code;} public void setCode(String code){this.code=code;}
  public Long getRoomId(){return roomId;} public void setRoomId(Long v){this.roomId=v;}
  public Long getGuestId(){return guestId;} public void setGuestId(Long v){this.guestId=v;}
  public LocalDate getCheckInDate(){return checkInDate;} public void setCheckInDate(LocalDate v){this.checkInDate=v;}
  public LocalDate getCheckOutDate(){return checkOutDate;} public void setCheckOutDate(LocalDate v){this.checkOutDate=v;}
  public int getOccupants(){return occupants;} public void setOccupants(int v){this.occupants=v;}
  public BigDecimal getNightlyRate(){return nightlyRate;} public void setNightlyRate(BigDecimal v){this.nightlyRate=v;}
  public ReservationStatus getStatus(){return status;} public void setStatus(ReservationStatus s){this.status=s;}
  public java.util.List<Charge> getCharges(){return charges;} public java.util.List<Payment> getPayments(){return payments;}
  public long getNights(){ return java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate); }
  public BigDecimal getRoomTotal(){ return nightlyRate.multiply(BigDecimal.valueOf(getNights())); }
  public BigDecimal getChargesTotal(){ return charges.stream().map(Charge::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add); }
  public BigDecimal getPaymentsTotal(){ return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add); }
  public BigDecimal getGrandTotal(){ return getRoomTotal().add(getChargesTotal()); }
  public BigDecimal getBalance(){ return getGrandTotal().subtract(getPaymentsTotal()); }
  @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof Reservation)) return false; return java.util.Objects.equals(id, ((Reservation)o).id); }
  @Override public int hashCode(){ return java.util.Objects.hash(id); }
}
