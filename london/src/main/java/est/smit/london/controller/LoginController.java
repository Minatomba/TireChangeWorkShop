package est.smit.london.controller;


import est.smit.london.DTO.UserLoginDTO;
import est.smit.london.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping
    public String loginUser(@ModelAttribute("user")
    UserLoginDTO userLoginDTO) {
        System.out.println("UserDTO"+userLoginDTO);
        UserDetails user = userService.loadUserByUsername(userLoginDTO.getUserName());
        if(user.getPassword().equals(passwordEncoder.encode(userLoginDTO.getPassword()))) {

            return "redirect:/dashboard";
        }
        else
            return "redirect:/login?error";
    }
}
