package est.smit.london.controller;


import est.smit.london.DTO.ReservationDTO;
import est.smit.london.entity.Reservation;
import est.smit.london.entity.User;
import est.smit.london.repository.UserRepository;
import est.smit.london.service.DefaultUserService;
import est.smit.london.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private DefaultUserServiceImpl userService;

    public DashboardController(DefaultUserServiceImpl userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    UserRepository userRepository;

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
    public String saveClient(@ModelAttribute("reservation") ReservationDTO reservationDTO) {
        return "dashboard";
    }



    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findUserByEmail(user.getUsername());
        return users.getName();
    }

}
