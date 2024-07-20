package est.smit.london.repository;

import est.smit.london.entity.MechanicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicDataRepository extends JpaRepository<MechanicData, Integer> {
    @Query(value = "select * from Reservation where reservation.vehicleType =:vehicleType and reservation.placeOfShop =:placeOfShop and reservation.filter_date =:date order By reservation.filter_date desc ", nativeQuery = true)
    List<MechanicData> findByVehicleTypeAndPlaceOfShop(String vehicleType, String placeOfShop, String date);
}
