package est.smit.london.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date filterDate;

    private String vehicleType;

    private String placeOfShop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlaceOfShop() {
        return placeOfShop;
    }

    public void setPlaceOfShop(String placeOfShop) {
        this.placeOfShop = placeOfShop;
    }
}
