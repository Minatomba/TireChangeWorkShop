package est.smit.london.helper;

import est.smit.london.DTO.BookingsDTO;
import est.smit.london.entity.Bookings;
import est.smit.london.entity.MechanicData;

public class ObjectCreationHelper {

    public static BookingsDTO createBookingsDTO(MechanicData mechanicData) {
        BookingsDTO bks = new BookingsDTO();

        bks.setMechanicName(mechanicData.getMechanicName());
        bks.setFilterDate(mechanicData.getFilterDate());
        bks.setVehicleType(mechanicData.getVehicleType());
        bks.setPlaceOfShop(mechanicData.getPlaceOfShop());
        bks.setTime(mechanicData.getTime());
        bks.setPrice(mechanicData.getPrice());
        bks.setTotalCalculated(0.0);

        return bks;
    }

    public static BookingsDTO createBookingsDTO(Bookings mechanicData) {
        BookingsDTO bks = new BookingsDTO();
        bks.setId(mechanicData.getId());

        bks.setMechanicName(mechanicData.getMechanicName());
        bks.setFilterDate(mechanicData.getFilterDate());
        bks.setVehicleType(mechanicData.getVehicleType());
        bks.setPlaceOfShop(mechanicData.getPlaceOfShop());
        bks.setTotalCalculated(mechanicData.getTotalCalculated());
        bks.setTime(mechanicData.getTime());
        if(mechanicData.isWorkStatus()== true) {
            bks.setWorkStatus("Active");
        } else {
            bks.setWorkStatus("Canceled");
        }

        return bks;
    }


}
