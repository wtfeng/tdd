package com.aiatss.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
	int parkingSpaces;
	private List<String> carNoList = new ArrayList<String>();
	

	public ParkingLot(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}

	public String park(String carNo) {
		if(parkingSpaces==0) {
			throw new NoParkingSpaceException();
		}
		if (carNoList.contains(carNo)){
			throw new NotAllowSameCarNoException();
		}
		parkingSpaces--;
		carNoList.add(carNo);
		return carNo;
	}

	public int getFreeParkingSpaces() {
		return parkingSpaces;
	}

	public void fetchCar(String carNo) {
		if(carNoList.contains(carNo)) {
			parkingSpaces++;
			carNoList.remove(carNo);
		}else {
			throw new NonExistCarNoException();
		}
	}

}
