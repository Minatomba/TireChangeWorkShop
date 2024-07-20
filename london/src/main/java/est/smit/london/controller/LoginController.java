package est.smit.london.controller;


import est.smit.london.DTO.UserLoginDTO;
import est.smit.london.service.DefaultUserService;
import est.smit.london.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private DefaultUserServiceImpl userService;

    /*
     * public LoginController(DefaultUserService userService) { super();
     * this.userService = userService; }
     */

    @GetMapping("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public void loginUser(@RequestBody
    UserLoginDTO userLoginDTO) {
       userService.loadUserByUsername(userLoginDTO.getUserName());
    }
}
