package com.example.hotel.repo;
import com.example.hotel.domain.*; import org.springframework.stereotype.Repository;
import java.time.LocalDate; import java.util.*; import java.util.concurrent.ConcurrentHashMap; import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors; import java.util.stream.Stream;
@Repository
public class InMemoryReservationRepository implements ReservationRepository {
  private final Map<Long, Reservation> data = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong(0);
  public java.util.List<Reservation> findAll(){ return new java.util.ArrayList<>(data.values()); }
  public java.util.Optional<Reservation> findById(Long id){ return java.util.Optional.ofNullable(data.get(id)); }
  public Reservation save(Reservation r){ if(r.getId()==null) r.setId(seq.incrementAndGet()); data.put(r.getId(), r); return r; }
  public Stream<Reservation> stream(){ return data.values().stream(); }
  public java.util.List<Reservation> findActiveByRoom(Long roomId){
    return data.values().stream().filter(r->java.util.Objects.equals(r.getRoomId(), roomId))
      .filter(r-> r.getStatus()==ReservationStatus.RESERVADA || r.getStatus()==ReservationStatus.EN_CURSO)
      .collect(Collectors.toList());
  }
  public java.util.List<Reservation> findOverlapping(Long roomId, LocalDate from, LocalDate to){
    return data.values().stream().filter(r->java.util.Objects.equals(r.getRoomId(), roomId))
      .filter(r-> r.getCheckInDate().isBefore(to) && r.getCheckOutDate().isAfter(from))
      .collect(Collectors.toList());
  }
}
