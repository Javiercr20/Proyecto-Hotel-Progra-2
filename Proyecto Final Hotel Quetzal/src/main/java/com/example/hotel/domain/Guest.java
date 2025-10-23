package com.example.hotel.domain;
import java.util.Objects;
public class Guest {
  private Long id; private String firstName; private String lastName; private String email; private String phone;
  public Guest() {}
  public Guest(Long id, String firstName, String lastName, String email, String phone){
    this.id=id; this.firstName=firstName; this.lastName=lastName; this.email=email; this.phone=phone;
  }
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getFirstName(){return firstName;} public void setFirstName(String v){this.firstName=v;}
  public String getLastName(){return lastName;} public void setLastName(String v){this.lastName=v;}
  public String getEmail(){return email;} public void setEmail(String v){this.email=v;}
  public String getPhone(){return phone;} public void setPhone(String v){this.phone=v;}
  public String getFullName(){return firstName + " " + lastName;}
  @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof Guest)) return false; return Objects.equals(id, ((Guest)o).id); }
  @Override public int hashCode(){ return Objects.hash(id); }
}
