package com.elevator.entity;

public class InternalRequest {
    int destinationFloor;

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public InternalRequest(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
