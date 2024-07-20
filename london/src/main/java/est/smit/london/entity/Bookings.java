package est.smit.london.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bookings")
@Getter
@Setter
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Double totalCalculated;

    private String filterDate;

    private String vehicleType;

    private String placeOfShop;

    private String mechanicName;

    private int userId;

    private String time;

    private String fileName;

    private boolean workStatus;

}
