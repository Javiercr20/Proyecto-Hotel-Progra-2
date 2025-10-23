package com.example.hotel.repo;
import com.example.hotel.domain.Guest; import java.util.*;
public interface GuestRepository {
  List<Guest> findAll();
  Optional<Guest> findById(Long id);
  Guest save(Guest guest);
}
