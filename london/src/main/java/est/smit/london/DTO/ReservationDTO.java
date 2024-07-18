package est.smit.london.DTO;

import java.sql.Date;

public class ReservationDTO {

    private String filterDate;

    private String vehicleType;

    private String placeOfShop;

    public String getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(String filterDate) {
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
