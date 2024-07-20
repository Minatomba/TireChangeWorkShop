package est.smit.london.controller;


import est.smit.london.DTO.UserLoginDTO;
import est.smit.london.service.DefaultUserService;
import est.smit.london.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private DefaultUserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*
     * public LoginController(DefaultUserService userService) { super();
     * this.userService = userService; }
     */

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping()
    public void loginUser(@ModelAttribute("user")
    UserLoginDTO userLoginDTO) {
       userService.loadUserByUsername(userLoginDTO.getUsername());
    }
}
