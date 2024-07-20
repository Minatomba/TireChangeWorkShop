package est.smit.london.controller;


import est.smit.london.DTO.BookingsDTO;
import est.smit.london.DTO.ReservationDTO;
import est.smit.london.entity.MechanicData;
import est.smit.london.entity.Bookings;
import est.smit.london.entity.User;
import est.smit.london.helper.ObjectCreationHelper;
import est.smit.london.repository.BookingsRepository;
import est.smit.london.repository.MechanicDataRepository;
import est.smit.london.repository.UserRepository;
import est.smit.london.service.DefaultUserService;
import est.smit.london.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private DefaultUserService userService;

    public DashboardController(DefaultUserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MechanicDataRepository mechanicDataRepository;

    @ModelAttribute("reservation")
    public ReservationDTO reservationDTO(){
        return new ReservationDTO();
    }

    @GetMapping
    public String displayDashBoard(Model model){
        String user = returnUsername();
        model.addAttribute("userDetails", user);
        return "dashboard";
    }

    @PostMapping
    public String filterMechanicData(@ModelAttribute("reservation") ReservationDTO reservationDTO, Model model) {
        List<MechanicData> mechanicData = mechanicDataRepository.findByVehicleTypeAndPlaceOfShop(reservationDTO.getVehicleType(), reservationDTO.getPlaceOfShop(), reservationDTO.getFilterDate());

        if (mechanicData.isEmpty()) {
            mechanicData = null;
        }

        String user = returnUsername();
        model.addAttribute("userDetails", user);
        model.addAttribute("mechanicData", mechanicData);
        model.addAttribute("reservation", reservationDTO);

        return "dashboard";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable int id, Model model) {
        Optional<MechanicData> mechanicData = mechanicDataRepository.findById(id);
        BookingsDTO bks = ObjectCreationHelper.createBookingsDTO(mechanicData.get());

        String user = returnUsername();
        model.addAttribute("userDetails", user);
        model.addAttribute("record", bks);

        return "book";

    }

    @PostMapping("/booking")
    public String finalBooking(@ModelAttribute("record") BookingsDTO bookingDTO, Model model) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        Bookings b = userService.updateBookings(bookingDTO, user);
        model.addAttribute("record", new BookingsDTO());

        return "redirect:/myBooking";
    }




    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findUserByEmail(user.getUsername());
        return users.getName();
    }

}
