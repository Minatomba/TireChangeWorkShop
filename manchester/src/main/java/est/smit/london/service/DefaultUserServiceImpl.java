package est.smit.london.service;

import est.smit.london.DTO.BookingsDTO;
import est.smit.london.DTO.UserRegisteredDTO;
import est.smit.london.entity.Bookings;
import est.smit.london.entity.Role;
import est.smit.london.entity.User;
import est.smit.london.repository.BookingsRepository;
import est.smit.london.repository.RoleRepository;
import est.smit.london.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultUserServiceImpl implements DefaultUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingsRepository bookingRepository;

    @Autowired
    private RoleRepository roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findUserByEmail(username);
        if (user == null) {
            System.out.println("Email or password is wrong: " + username);
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }


    @Override
    public User save(UserRegisteredDTO userRegisteredDTO) {
        Role role = new Role();

        if (userRegisteredDTO.getRole().equals("USER"))
            role = roleRepo.findByRole("USER");
        else if (userRegisteredDTO.getRole().equals("ADMIN")) {
            role = roleRepo.findByRole("ADMIN");
        }
        User user = new User();
        user.setEmail(userRegisteredDTO.getEmail_id());
        user.setName(userRegisteredDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        user.setRole(role);

        return userRepo.save(user);
    }

    @Override
    public Bookings updateBookings(BookingsDTO bookingDTO, UserDetails user) {
        Bookings booking = new Bookings();
        String email = user.getUsername();
        User users = userRepo.findUserByEmail(email);
        booking.setMechanicName(bookingDTO.getMechanicName());
        booking.setFilterDate(bookingDTO.getFilterDate());
        booking.setVehicleType(bookingDTO.getVehicleType());
        booking.setPlaceOfShop(bookingDTO.getPlaceOfShop());
        booking.setTotalCalculated(bookingDTO.getTotalCalculated());
        booking.setTime(bookingDTO.getTime());
        booking.setUserId(booking.getUserId());
        booking.setWorkStatus(true);
        return bookingRepository.save(booking);

    }

    @Override
    public void sendEmail(BookingsDTO bks, User user, String fileName) {

    }
}