package com.example.hotel.repo;
import com.example.hotel.domain.Room; import java.util.*;
public interface RoomRepository {
  List<Room> findAll();
  Optional<Room> findById(Long id);
  Optional<Room> findByNumber(String number);
  Room save(Room room);
  void deleteById(Long id);
}
