package com.example.hotel.web.dto;
import jakarta.validation.constraints.*; import org.springframework.format.annotation.DateTimeFormat; import java.time.LocalDate;
public class ReservationForm {
  @NotNull private Long roomId;
  @NotBlank private String guestFirstName; @NotBlank private String guestLastName;
  private String guestEmail; private String guestPhone;
  @NotNull @DateTimeFormat(pattern="yyyy-MM-dd") @Future private LocalDate checkIn;
  @NotNull @DateTimeFormat(pattern="yyyy-MM-dd") @Future private LocalDate checkOut;
  @Min(1) private int occupants = 1;
  public Long getRoomId(){return roomId;} public void setRoomId(Long v){roomId=v;}
  public String getGuestFirstName(){return guestFirstName;} public void setGuestFirstName(String v){guestFirstName=v;}
  public String getGuestLastName(){return guestLastName;} public void setGuestLastName(String v){guestLastName=v;}
  public String getGuestEmail(){return guestEmail;} public void setGuestEmail(String v){guestEmail=v;}
  public String getGuestPhone(){return guestPhone;} public void setGuestPhone(String v){guestPhone=v;}
  public LocalDate getCheckIn(){return checkIn;} public void setCheckIn(LocalDate v){checkIn=v;}
  public LocalDate getCheckOut(){return checkOut;} public void setCheckOut(LocalDate v){checkOut=v;}
  public int getOccupants(){return occupants;} public void setOccupants(int v){occupants=v;}
}
