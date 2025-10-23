package com.example.hotel.domain;
import java.math.BigDecimal; import java.util.Objects;
public class Room {
  private Long id; private String number; private RoomType type; private RoomStatus status; private boolean blocked; private BigDecimal ratePerNight; private String notes;
  public Room() {}
  public Room(Long id, String number, RoomType type, RoomStatus status, boolean blocked, BigDecimal ratePerNight, String notes){
    this.id=id; this.number=number; this.type=type; this.status=status; this.blocked=blocked; this.ratePerNight=ratePerNight; this.notes=notes;
  }
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getNumber(){return number;} public void setNumber(String number){this.number=number;}
  public RoomType getType(){return type;} public void setType(RoomType type){this.type=type;}
  public RoomStatus getStatus(){return status;} public void setStatus(RoomStatus status){this.status=status;}
  public boolean isBlocked(){return blocked;} public void setBlocked(boolean blocked){this.blocked=blocked;}
  public BigDecimal getRatePerNight(){return ratePerNight;} public void setRatePerNight(BigDecimal r){this.ratePerNight=r;}
  public String getNotes(){return notes;} public void setNotes(String notes){this.notes=notes;}
  @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof Room)) return false; return Objects.equals(id, ((Room)o).id); }
  @Override public int hashCode(){ return Objects.hash(id); }
}
