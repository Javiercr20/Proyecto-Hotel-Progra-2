package com.example.hotel.web;
import com.example.hotel.exception.BusinessException; import com.example.hotel.service.HotelService; import com.example.hotel.web.dto.ReservationForm;
import jakarta.validation.Valid; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
@Controller @RequestMapping("/reservations")
public class ReservationController {
  private final HotelService service; public ReservationController(HotelService service){ this.service=service; }
  @GetMapping public String list(Model model){
    model.addAttribute("reservations", service.listReservations());
    model.addAttribute("rooms", service.listRooms());
    model.addAttribute("form", new ReservationForm());
    return "reservations";
  }
  @PostMapping public String create(@ModelAttribute("form") @Valid ReservationForm form, BindingResult br, Model model){
    if(br.hasErrors()) return list(model);
    try {
      var g = service.createGuest(form.getGuestFirstName(), form.getGuestLastName(), form.getGuestEmail(), form.getGuestPhone());
      service.createReservation(form.getRoomId(), g.getId(), form.getCheckIn(), form.getCheckOut(), form.getOccupants());
      return "redirect:/reservations";
    } catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); return list(model); }
  }
  @PostMapping("/{id}/checkin") public String checkIn(@PathVariable Long id, Model model){
    try { service.checkIn(id); return "redirect:/reservations"; } catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); return list(model); }
  }
  @PostMapping("/{id}/checkout") public String checkOut(@PathVariable Long id, Model model){
    try { service.checkOut(id); return "redirect:/reservations"; } catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); return list(model); }
  }
  @PostMapping("/{id}/charge") public String addCharge(@PathVariable Long id, @RequestParam String description, @RequestParam BigDecimal amount, @RequestParam(defaultValue="OTROS") String category, Model model){
    try { service.addCharge(id, description, amount, category); return "redirect:/reservations"; } catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); return list(model); }
  }
  @PostMapping("/{id}/payment") public String addPayment(@PathVariable Long id, @RequestParam BigDecimal amount, @RequestParam String method, @RequestParam(required=false) String reference, Model model){
    try { service.addPayment(id, amount, method, reference); return "redirect:/reservations"; } catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); return list(model); }
  }
}
