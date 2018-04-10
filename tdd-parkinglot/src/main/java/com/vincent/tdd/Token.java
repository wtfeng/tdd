package com.vincent.tdd;

public class Token {
	private ParkingSpace parkingSpace;

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}
	
	public Token(ParkingSpace parkingSpace) {
		this.parkingSpace=parkingSpace;
	}

}
