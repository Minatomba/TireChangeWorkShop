package est.smit.london.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserLoginDTO {
    private String username;

    private String password;

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}