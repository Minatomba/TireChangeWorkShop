package est.smit.london.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingsDTO {

    private int id;
    private String filterDate;
    private String vehicleType;
    private String placeOfShop;
    private Double price;
    private String MechanicName;
    private String time;
    private Double totalCalculated;
    private String workStatus;

}
