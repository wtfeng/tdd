package com.vincent.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
	ParkingSpace[] parkingSpaces;
	private Map<Token, Car> parkedCars = new HashMap<Token, Car>();

	public ParkingLot(int parkingSpaces) {
		this.parkingSpaces = new ParkingSpace[parkingSpaces];
		for (int i = 0; i < this.parkingSpaces.length; i++) {
			this.parkingSpaces[i] = new ParkingSpace(i);
		}
	}

	public Token park(Car car) {
		for (ParkingSpace parkingSpace : parkingSpaces) {
			if(parkingSpace.isAvailable()) {
				parkingSpace.setAvailable(false);
				Token token = new Token(parkingSpace);
				parkedCars.put(token, car);
				return token;
			}
		}
		throw new NoParkingSpaceException();
	}

	public int getavilableParkingSpaces() {
		return parkingSpaces.length - parkedCars.size();
	}

	public Car fetchCarBy(Token token) {
		Car car = parkedCars.remove(token);
		if (car == null)
			throw new CarNotExistException();
		return car;
	}

}
