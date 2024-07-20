package est.smit.london.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
public class MechanicData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filterDate;

    private String vehicleType;

    private String placeOfShop;

    private Double price;

    private String mechanicName;

    private String time;
}
