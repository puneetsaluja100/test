package com.elevator.entity;

import com.elevator.ElevatorRequestManager;
import com.elevator.enums.Direction;

public class ButtonPanel {
    Direction direction;

    Building building;

    int requestedSourceFloor;

    int requestedDestinationFloor;

    ElevatorRequestManager elevatorRequestManager;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorRequestManager getElevatorRequestManager() {
        return elevatorRequestManager;
    }

    public void setElevatorRequestManager(ElevatorRequestManager elevatorRequestManager) {
        this.elevatorRequestManager = elevatorRequestManager;
    }

    public ButtonPanel(Building building) {
        this.building = building;
        this.elevatorRequestManager = new ElevatorRequestManager(building.getElevators());
    }

    public int getRequestedSourceFloor() {
        return requestedSourceFloor;
    }

    public void setRequestedSourceFloor(int requestedSourceFloor) {
        this.requestedSourceFloor = requestedSourceFloor;
    }

    public int getRequestedDestinationFloor() {
        return requestedDestinationFloor;
    }

    public void setRequestedDestinationFloor(int requestedDestinationFloor) {
        this.requestedDestinationFloor = requestedDestinationFloor;
    }

    public void acceptUserRequest(int requestedFromSourceFloor, int requestedDestinationFloor, Direction direction){
        ExternalRequest er = new ExternalRequest(direction, requestedFromSourceFloor);
        InternalRequest ir = new InternalRequest(requestedDestinationFloor);
        ElevatorRequest request1 = new ElevatorRequest(ir, er);
        this.elevatorRequestManager.placeRequest(request1);
    }
}
