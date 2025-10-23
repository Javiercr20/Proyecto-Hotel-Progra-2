package com.example.hotel.config;
import com.example.hotel.domain.*; import com.example.hotel.repo.*; import com.example.hotel.service.HotelService;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal; import java.time.LocalDate; import java.util.List;
@Configuration
public class SampleDataConfig {
  @Bean CommandLineRunner loadData(RoomRepository roomRepo, GuestRepository guestRepo, HotelService hotelService){
    return args -> {
      if (roomRepo.findAll().size() < 8) {
        roomRepo.save(new Room(null, "103", RoomType.SIMPLE, RoomStatus.DISPONIBLE, false, new BigDecimal("375.00"), "Tarifa GTQ"));
        roomRepo.save(new Room(null, "104", RoomType.DOBLE, RoomStatus.DISPONIBLE, false, new BigDecimal("525.00"), "Tarifa GTQ"));
        roomRepo.save(new Room(null, "202", RoomType.FAMILIAR, RoomStatus.DISPONIBLE, false, new BigDecimal("750.00"), "Familiar"));
        roomRepo.save(new Room(null, "203", RoomType.SUITE, RoomStatus.DISPONIBLE, false, new BigDecimal("980.00"), "Suite Quetzal"));
        roomRepo.save(new Room(null, "301", RoomType.SUITE, RoomStatus.DISPONIBLE, true, new BigDecimal("1100.00"), "Mantenimiento"));
      }
      List<Guest> existing = guestRepo.findAll();
      if (existing.size() < 5) {
        guestRepo.save(new Guest(null, "María", "García", "maria.gtz@example.com", "+502 5551 0001"));
        guestRepo.save(new Guest(null, "José", "López", "jose.lopez@example.com", "+502 5551 0002"));
        guestRepo.save(new Guest(null, "Lucía", "Ramírez", "lucia.ramirez@example.com", "+502 5551 0003"));
        guestRepo.save(new Guest(null, "Diego", "González", "diego.gonzalez@example.com", "+502 5551 0004"));
      }
      LocalDate today = LocalDate.now();
      LocalDate nextFri = today.plusDays((5 - today.getDayOfWeek().getValue() + 7) % 7);
      LocalDate nextSun = nextFri.plusDays(2);
      LocalDate nextWeekMon = today.plusWeeks(1).with(java.time.DayOfWeek.MONDAY);
      var rooms = roomRepo.findAll(); var guests = guestRepo.findAll();
      if (!rooms.isEmpty() && !guests.isEmpty()) {
        try { hotelService.createReservation(rooms.get(0).getId(), guests.get(0).getId(), nextFri, nextSun, 2); } catch (Exception ignore) {}
        try { hotelService.createReservation(rooms.get(1).getId(), guests.get(1).getId(), nextWeekMon, nextWeekMon.plusDays(3), 1); } catch (Exception ignore) {}
      }
    };
  }
}
