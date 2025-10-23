package com.example.hotel.repo;
import com.example.hotel.domain.*; import org.springframework.stereotype.Repository;
import java.math.BigDecimal; import java.util.*; import java.util.concurrent.ConcurrentHashMap; import java.util.concurrent.atomic.AtomicLong;
@Repository
public class InMemoryRoomRepository implements RoomRepository {
  private final Map<Long, Room> data = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong(0);
  public InMemoryRoomRepository(){
    save(new Room(null, "101", RoomType.SIMPLE, RoomStatus.DISPONIBLE, false, new BigDecimal("350.00"), ""));
    save(new Room(null, "102", RoomType.DOBLE, RoomStatus.DISPONIBLE, false, new BigDecimal("500.00"), ""));
    save(new Room(null, "201", RoomType.SUITE, RoomStatus.DISPONIBLE, false, new BigDecimal("900.00"), ""));
  }
  public java.util.List<Room> findAll(){ return new java.util.ArrayList<>(data.values()); }
  public java.util.Optional<Room> findById(Long id){ return java.util.Optional.ofNullable(data.get(id)); }
  public java.util.Optional<Room> findByNumber(String number){ return data.values().stream().filter(r->java.util.Objects.equals(r.getNumber(), number)).findFirst(); }
  public Room save(Room room){ if(room.getId()==null) room.setId(seq.incrementAndGet()); data.put(room.getId(), room); return room; }
  public void deleteById(Long id){ data.remove(id); }
}
