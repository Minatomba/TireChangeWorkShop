package est.smit.london.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class ReservationDTO {

    private String filterDate;

    private String vehicleType;

    private String placeOfShop;

}
