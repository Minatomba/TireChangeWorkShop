package est.smit.london.service;
import est.smit.london.DTO.BookingsDTO;
import est.smit.london.entity.Bookings;
import est.smit.london.entity.User;
import est.smit.london.DTO.UserRegisteredDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface DefaultUserService extends UserDetailsService {

    User save(UserRegisteredDTO userRegisteredDTO);

    Bookings updateBookings(BookingsDTO bookingDTO, UserDetails user);

    void sendEmail(BookingsDTO bks, User user, String fileName);
}
