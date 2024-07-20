package est.smit.london.repository;

import est.smit.london.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

    List<Bookings> findByUserId(int userId);
}
