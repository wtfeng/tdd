package com.aiatss.tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ParkingLotTest {
	@Test
	public void park_car_when_with_aviable_space_the_number_should_minors_1() {
		ParkingLot parkingLot = new ParkingLot(1);
		String carNo = "123";
		assertEquals(carNo, parkingLot.park(carNo));
	}

	@Test(expected = NoParkingSpaceException.class)
	public void park_car_when_no_free_space() {
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.park("123");
		parkingLot.park("124");
	}
	
	@Test(expected = NotAllowSameCarNoException.class)
	public void park_the_same_car_no() {
		ParkingLot parkingLot = new ParkingLot(2);
		parkingLot.park("123");
		parkingLot.park("123");
	}
	
	@Test 
	public void should_return_total_number_of_free_spaces() {
		int parkingSpaces = 2;
		ParkingLot parkingLot = new ParkingLot(parkingSpaces);
		assertEquals(parkingSpaces,parkingLot.getFreeParkingSpaces());
	}
	
	@Test 
	public void fetch_a_parked_car() {
		int parkingSpaces = 2;
		ParkingLot parkingLot = new ParkingLot(parkingSpaces);
		String carNo="123";
		parkingLot.park(carNo);
		parkingLot.fetchCar(carNo);
		assertEquals(2,parkingLot.getFreeParkingSpaces());
	}
	
	@Test(expected = NonExistCarNoException.class) 
	public void fetch_a_non_exist_car() {
		int parkingSpaces = 2;
		ParkingLot parkingLot = new ParkingLot(parkingSpaces);
		String carNo="123";
		parkingLot.park(carNo);
		String carNo2 = "124";
		parkingLot.fetchCar(carNo2);
	}
}
