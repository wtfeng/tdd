package com.vincent.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
	List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

	/**
	 * The sequence of the parking lots will be used as priority of parking in which parking lots. 
	 * @param parkingLots
	 */
	public ParkingBoy(ParkingLot... parkingLots) {
		if (parkingLots == null)
			throw new ParkingLotIsMandatoryException();
		for (ParkingLot parkingLot : parkingLots) {
			this.parkingLots.add(parkingLot);
		}
	}

	public Token park(Car car) {
		Token token = null;
		for (ParkingLot parkingLot : parkingLots) {
			if (parkingLot.getavilableParkingSpaces() > 0) {
				token = parkingLot.park(car);
				break;
			}
		}
		if (token == null) {
			throw new NoParkingSpaceException();
		}
		return token;
	}

	public Car fetchCarBy(Token token) {
		Car fetchedCar = null;
		for (ParkingLot parkingLot : parkingLots) {
			try {
				fetchedCar = parkingLot.fetchCarBy(token);
				token.getParkingSpace().setAvailable(true);
				return fetchedCar;
			} catch (CarNotExistException e) {
			}
		}
		if (fetchedCar == null) {
			throw new CarNotExistException();
		}
		return fetchedCar;
	}

}
