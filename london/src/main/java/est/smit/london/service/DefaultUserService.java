package est.smit.london.service;
import est.smit.london.entity.User;
import est.smit.london.DTO.UserRegisteredDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface DefaultUserService extends UserDetailsService {

    User save(UserRegisteredDTO userRegisteredDTO);

}
