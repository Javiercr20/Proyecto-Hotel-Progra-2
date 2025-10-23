package com.example.hotel.repo;
import com.example.hotel.domain.Reservation; import java.time.LocalDate; import java.util.*; import java.util.stream.Stream;
public interface ReservationRepository {
  List<Reservation> findAll();
  Optional<Reservation> findById(Long id);
  Reservation save(Reservation r);
  Stream<Reservation> stream();
  List<Reservation> findActiveByRoom(Long roomId);
  List<Reservation> findOverlapping(Long roomId, LocalDate from, LocalDate to);
}
