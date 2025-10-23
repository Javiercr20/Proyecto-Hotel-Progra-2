package com.example.hotel.web;
import com.example.hotel.service.HotelService; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/guests")
public class GuestController {
  private final HotelService service; public GuestController(HotelService service){ this.service=service; }
  @GetMapping public String list(Model model){ model.addAttribute("guests", service.listGuests()); return "guests"; }
}
