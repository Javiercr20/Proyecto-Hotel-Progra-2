package com.example.hotel.repo;
import com.example.hotel.domain.Guest; import org.springframework.stereotype.Repository;
import java.util.*; import java.util.concurrent.ConcurrentHashMap; import java.util.concurrent.atomic.AtomicLong;
@Repository
public class InMemoryGuestRepository implements GuestRepository {
  private final Map<Long, Guest> data = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong(0);
  public InMemoryGuestRepository(){
    save(new Guest(null, "Ana", "López", "ana@example.com", "+502 5550 0001"));
    save(new Guest(null, "Carlos", "Pérez", "carlos@example.com", "+502 5550 0002"));
  }
  public java.util.List<Guest> findAll(){ return new java.util.ArrayList<>(data.values()); }
  public java.util.Optional<Guest> findById(Long id){ return java.util.Optional.ofNullable(data.get(id)); }
  public Guest save(Guest g){ if(g.getId()==null) g.setId(seq.incrementAndGet()); data.put(g.getId(), g); return g; }
}
