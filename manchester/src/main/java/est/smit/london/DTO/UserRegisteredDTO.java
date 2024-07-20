package est.smit.london.DTO;

import java.util.Collection;
import java.util.Set;

import est.smit.london.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisteredDTO {

    private String name;

    private String email_id;

    private String password;

    private String role;


}

