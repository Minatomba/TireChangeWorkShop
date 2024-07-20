package est.smit.london.controller;

import est.smit.london.entity.MechanicData;
import est.smit.london.entity.User;
import est.smit.london.repository.MechanicDataRepository;
import est.smit.london.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminScreen")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MechanicDataRepository mechanicDataRepository;

    @ModelAttribute("mechanicDetails")
    public MechanicData mechanicData() {
        return new MechanicData();
    }

    @GetMapping
    public String displayDashboard(Model model) {
        String user = returnUsername();
        model.addAttribute("userDetails", user);
        return "adminScreen";
    }

    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findUserByEmail(user.getUsername());
        return users.getName();
    }

    @PostMapping
    public String saveMechanicData(@ModelAttribute("mechanicDetails") MechanicData mechanicData, Model model){
        String user = returnUsername();
        model.addAttribute("userDetails", user);
        mechanicDataRepository.save(mechanicData);
        model.addAttribute("mechanicDetails", new MechanicData());
        return "redirect:/adminScreen?success";
    }

}
