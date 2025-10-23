package com.example.hotel.web;
import com.example.hotel.exception.BusinessException; import com.example.hotel.service.HotelService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/rooms")
public class RoomController {
  private final HotelService service; public RoomController(HotelService service){ this.service=service; }
  @GetMapping public String list(Model model){ model.addAttribute("rooms", service.listRooms()); return "rooms"; }
  @PostMapping("/{id}/toggle-block")
  public String toggle(@PathVariable Long id, Model model){
    try { service.toggleBlockRoom(id); return "redirect:/rooms"; }
    catch (BusinessException ex){ model.addAttribute("error", ex.getMessage()); model.addAttribute("rooms", service.listRooms()); return "rooms"; }
  }
}
