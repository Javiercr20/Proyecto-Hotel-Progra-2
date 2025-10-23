package com.example.hotel.service;
import com.example.hotel.domain.*; import com.example.hotel.exception.BusinessException;
import com.example.hotel.repo.*; import org.springframework.stereotype.Service;
import java.math.BigDecimal; import java.time.LocalDate; import java.util.List; import java.util.stream.Collectors;
@Service
public class HotelService {
  private final RoomRepository roomRepo; private final GuestRepository guestRepo; private final ReservationRepository reservationRepo;
  public HotelService(RoomRepository roomRepo, GuestRepository guestRepo, ReservationRepository reservationRepo){
    this.roomRepo=roomRepo; this.guestRepo=guestRepo; this.reservationRepo=reservationRepo;
  }
  // Rooms
  public List<Room> listRooms(){ return roomRepo.findAll(); }
  public Room toggleBlockRoom(Long id){
    Room r = roomRepo.findById(id).orElseThrow(()->new BusinessException("Habitación no encontrada"));
    r.setBlocked(!r.isBlocked());
    r.setStatus(r.isBlocked()? RoomStatus.FUERA_DE_SERVICIO : RoomStatus.DISPONIBLE);
    return roomRepo.save(r);
  }
  public List<Room> availableRooms(LocalDate from, LocalDate to){
    return roomRepo.findAll().stream().filter(r->!r.isBlocked())
        .filter(r->reservationRepo.findOverlapping(r.getId(), from, to).isEmpty()).collect(Collectors.toList());
  }
  // Guests
  public List<Guest> listGuests(){ return guestRepo.findAll(); }
  public Guest createGuest(String firstName, String lastName, String email, String phone){
    var g = new Guest(null, firstName, lastName, email, phone); return guestRepo.save(g);
  }
  // Reservations
  public List<Reservation> listReservations(){ return reservationRepo.findAll(); }
  public Reservation createReservation(Long roomId, Long guestId, LocalDate checkIn, LocalDate checkOut, int occupants){
    if(checkIn==null || checkOut==null || !checkOut.isAfter(checkIn)) throw new BusinessException("Rango de fechas inválido");
    Room room = roomRepo.findById(roomId).orElseThrow(()->new BusinessException("Habitación inexistente"));
    if(room.isBlocked()) throw new BusinessException("La habitación está bloqueada");
    if(!reservationRepo.findOverlapping(roomId, checkIn, checkOut).isEmpty()) throw new BusinessException("Conflicto de disponibilidad");
    guestRepo.findById(guestId).orElseThrow(()->new BusinessException("Huésped inexistente"));
    Reservation res = new Reservation(null, roomId, guestId, checkIn, checkOut, occupants, room.getRatePerNight());
    return reservationRepo.save(res);
  }
  public Reservation addCharge(Long reservationId, String description, BigDecimal amount, String category){
    var r = reservationRepo.findById(reservationId).orElseThrow(()->new BusinessException("Reserva no encontrada"));
    if(amount.signum()<0) throw new BusinessException("Monto inválido");
    r.getCharges().add(new Charge(description, amount, LocalDate.now(), category));
    return reservationRepo.save(r);
  }
  public Reservation addPayment(Long reservationId, BigDecimal amount, String method, String reference){
    var r = reservationRepo.findById(reservationId).orElseThrow(()->new BusinessException("Reserva no encontrada"));
    if(amount.signum()<=0) throw new BusinessException("Pago inválido");
    r.getPayments().add(new Payment(amount, LocalDate.now(), method, reference));
    return reservationRepo.save(r);
  }
  public Reservation checkIn(Long reservationId){
    var r = reservationRepo.findById(reservationId).orElseThrow(()->new BusinessException("Reserva no encontrada"));
    if(r.getStatus()!=ReservationStatus.RESERVADA) throw new BusinessException("La reserva no está RESERVADA");
    var room = roomRepo.findById(r.getRoomId()).orElseThrow(()->new BusinessException("Habitación inexistente"));
    if(room.getStatus()==RoomStatus.OCUPADA) throw new BusinessException("La habitación ya está ocupada");
    room.setStatus(RoomStatus.OCUPADA); roomRepo.save(room);
    r.setStatus(ReservationStatus.EN_CURSO);
    return reservationRepo.save(r);
  }
  public Reservation checkOut(Long reservationId){
    var r = reservationRepo.findById(reservationId).orElseThrow(()->new BusinessException("Reserva no encontrada"));
    if(r.getStatus()!=ReservationStatus.EN_CURSO) throw new BusinessException("La reserva no está EN_CURSO");
    var room = roomRepo.findById(r.getRoomId()).orElseThrow(()->new BusinessException("Habitación inexistente"));
    room.setStatus(RoomStatus.DISPONIBLE); roomRepo.save(room);
    r.setStatus(ReservationStatus.FINALIZADA);
    return reservationRepo.save(r);
  }
}
