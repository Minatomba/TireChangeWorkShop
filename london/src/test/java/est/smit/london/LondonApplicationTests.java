package est.smit.london;

import est.smit.london.DTO.ReservationDTO;
import est.smit.london.DTO.UserRegisteredDTO;
import est.smit.london.entity.MechanicData;
import est.smit.london.repository.MechanicDataRepository;
import est.smit.london.repository.UserRepository;
import est.smit.london.service.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class LondonApplicationTests {

	@Autowired
	DefaultUserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	MechanicDataRepository mechanicDataRepository;

	@Test
	public void registerAndLoginWithUserAccount(){
		UserRegisteredDTO userRegisteredDTO = new UserRegisteredDTO();
		userRegisteredDTO.setEmail_id("temp@gmail.com");
		userRegisteredDTO.setName("Temp");
		userRegisteredDTO.setPassword("12345");
		userRegisteredDTO.setRole("USER");
		userService.save(userRegisteredDTO);
		Assert.notNull(userRepo.findUserByEmail("temp@gmail.com"), "User found in DB");
		UserDetails user = userService.loadUserByUsername("temp@gmail.com");
		Assert.notNull(user, "Logined successfully");
	}

	@Test
	public void registerAndLoginAdminAccount() {
		UserRegisteredDTO userRegisteredDTO = new UserRegisteredDTO();
		userRegisteredDTO.setName("ABC");
		userRegisteredDTO.setEmail_id("temp1@gmail.com");
		userRegisteredDTO.setPassword("12345");
		userRegisteredDTO.setRole("ADMIN");
		userService.save(userRegisteredDTO);
		Assert.notNull(userRepo.findUserByEmail("temp1@gmail.com"), "Register successful");
		UserDetails user = userService.loadUserByUsername("temp1@gmail.com");
		Assert.notNull(user, "User Login Successful");
	}

	@Test
	public void saveMechanicDataByAdminAccount(){
		UserRegisteredDTO userRegisteredDTO = new UserRegisteredDTO();
		userRegisteredDTO.setName("ABC");
		userRegisteredDTO.setEmail_id("temp12@gmail.com");
		userRegisteredDTO.setPassword("12345");
		userRegisteredDTO.setRole("ADMIN");
		userService.save(userRegisteredDTO);
		Assert.notNull(userRepo.findUserByEmail("temp12@gmail.com"), "Register successful");
		UserDetails user = userService.loadUserByUsername("temp12@gmail.com");
		Assert.notNull(user, "User Login Successful");
		MechanicData mechanicData = new MechanicData();
		mechanicData.setMechanicName("TestBus");
		mechanicData.setVehicleType("ND");
		mechanicData.setPlaceOfShop("AMT");
		mechanicData.setFilterDate("2022-11-10");
		mechanicData.setTime("11:25");
		mechanicData.setPrice(40.0);
		MechanicData md = mechanicDataRepository.save(mechanicData);
		Assert.notNull(md, "Mechanic data Saved Successfully");
	}

	@Test
	public void fetchBusData() {
		ReservationDTO rs = new ReservationDTO();
		rs.setVehicleType("vehicle");
		rs.setPlaceOfShop("London");
		rs.setFilterDate("2022-11-10");
		List<MechanicData> bs = mechanicDataRepository.findByVehicleTypeAndPlaceOfShop(rs.getVehicleType(), rs.getPlaceOfShop(), rs.getFilterDate());
		Assert.notEmpty(bs, "Mechanic Data available with above filters ");
	}


}
