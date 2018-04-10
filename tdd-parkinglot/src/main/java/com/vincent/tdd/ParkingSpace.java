package com.vincent.tdd;

public class ParkingSpace {
	private int parkingSpaceNumber;
	
	private boolean issAvailable = true;

	public ParkingSpace(int parkingSpaceNumber) {
		this.parkingSpaceNumber = parkingSpaceNumber;
	}

	public boolean isPriorThan(ParkingSpace parkingSpace) {
		if (this.parkingSpaceNumber < parkingSpace.parkingSpaceNumber)
			return true;
		return false;
	}

	public boolean isAvailable() {
		return issAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.issAvailable = isAvailable;
	}

}
