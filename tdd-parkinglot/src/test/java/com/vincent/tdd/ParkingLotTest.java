package com.vincent.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.vincent.tdd.Car;
import com.vincent.tdd.CarNotExistException;
import com.vincent.tdd.NoParkingSpaceException;
import com.vincent.tdd.ParkingBoy;
import com.vincent.tdd.ParkingLot;
import com.vincent.tdd.Token;

/**
 * Unit test for simple App.
 */
public class ParkingLotTest {
	@Test
	public void should_return_token_after_a_car_parked() {
		ParkingLot parkingLot = new ParkingLot(1);
		Car car = new Car();

		Token token = parkingLot.park(car);

		assertNotNull(token);
	}

	@Test(expected = NoParkingSpaceException.class)
	public void park_car_when_no_free_space() {
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.park(new Car());

		parkingLot.park(new Car());
	}

	@Test
	public void should_return_correct_number_of_avaible_spaces() {
		int parkingCapacity = 2;
		ParkingLot parkingLot = new ParkingLot(parkingCapacity);

		assertEquals(parkingCapacity, parkingLot.getavilableParkingSpaces());
	}

	@Test
	public void fetch_a_parked_car() {
		int parkingCapacity = 2;
		ParkingLot parkingLot = new ParkingLot(parkingCapacity);
		Car car = new Car();
		Token token = parkingLot.park(car);

		Car fetchedCar = parkingLot.fetchCarBy(token);

		assertEquals(car, fetchedCar);
		assertEquals(2, parkingLot.getavilableParkingSpaces());
	}

	@Test(expected = CarNotExistException.class)
	public void fetch_a_non_exist_car() {
		int parkingCapacity = 2;
		ParkingLot parkingLot = new ParkingLot(parkingCapacity);
		Car car = new Car();

		parkingLot.park(car);

		parkingLot.fetchCarBy(new Token(null));
	}

	@Test
	public void should_park_in_the_first_parking_lot_first() {
		int parkingCapacity = 2;
		ParkingLot parkingLot1 = new ParkingLot(parkingCapacity);
		ParkingLot parkingLot2 = new ParkingLot(parkingCapacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();

		Token token1 = parkingBoy.park(car1);
		Token token2 = parkingBoy.park(car2);
		Car fetchedCar1 = parkingLot1.fetchCarBy(token1);
		Car fetchedCar2 = parkingLot1.fetchCarBy(token2);

		assertEquals(car1, fetchedCar1);
		assertEquals(car2, fetchedCar2);
	}

	@Test
	public void should_park_in_another_parking_lot_when_full() {
		int parkingCapacity = 1;
		ParkingLot parkingLot1 = new ParkingLot(parkingCapacity);
		ParkingLot parkingLot2 = new ParkingLot(parkingCapacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();

		Token token1 = parkingBoy.park(car1);
		Token token2 = parkingBoy.park(car2);

		Car fetchedCar1 = parkingLot1.fetchCarBy(token1);
		Car fetchedCar2 = parkingLot2.fetchCarBy(token2);

		assertEquals(car1, fetchedCar1);
		assertEquals(car2, fetchedCar2);
	}

	@Test(expected = NoParkingSpaceException.class)
	public void should_fail_to_park_when_all_parking_lots_are_full() {
		int parkingCapacity = 1;
		ParkingLot parkingLot1 = new ParkingLot(parkingCapacity);
		ParkingLot parkingLot2 = new ParkingLot(parkingCapacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		
		parkingBoy.park(car1);
		parkingBoy.park(car2);
		parkingBoy.park(car3);
	}
	
	@Test
	public void should_park_in_sequence_first_parking_lot_first() {
		int parkingCapacity1 = 1;
		int parkingCapacity2 = 2;
		ParkingLot parkingLot1 = new ParkingLot(parkingCapacity1);
		ParkingLot parkingLot2 = new ParkingLot(parkingCapacity2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		Token token1 = parkingBoy.park(car1);
		parkingBoy.park(car2);
		parkingBoy.fetchCarBy(token1);
		Token token3 = parkingBoy.park(car3);
		Car fetchedCar3 = parkingLot1.fetchCarBy(token3);
		
		assertEquals(car3,fetchedCar3);
	}
	
	@Test
	public void should_park_in_sequence_among_the_available_parking_space_in_one_parking_lot() {
		int parkingCapacity = 3;
		ParkingLot parkingLot1 = new ParkingLot(parkingCapacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		Car car4 = new Car();
		Token token1 = parkingBoy.park(car1);
		Token token2 = parkingBoy.park(car2);
		Token token3 = parkingBoy.park(car3);
		parkingBoy.fetchCarBy(token1);
		parkingBoy.fetchCarBy(token3);
		Token token4 = parkingBoy.park(car4);
		
		assertTrue(token4.getParkingSpace().isPriorThan(token2.getParkingSpace()));
	}
}
