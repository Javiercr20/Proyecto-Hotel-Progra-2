package com.example.hotel.web;
import com.example.hotel.service.HotelService; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
  private final HotelService service;
  public HomeController(HotelService service){ this.service = service; }
  @GetMapping("/") public String dashboard(Model model){
    model.addAttribute("rooms", service.listRooms());
    model.addAttribute("reservations", service.listReservations());
    model.addAttribute("guests", service.listGuests());
    return "dashboard";
  }
}
