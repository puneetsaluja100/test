package com.elevator.entity;

import com.elevator.enums.Direction;

public class ExternalRequest {
    private Direction direction;
    private int sourceFloor;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    public ExternalRequest(Direction direction, int sourceFloor) {
        this.direction = direction;
        this.sourceFloor = sourceFloor;
    }
}
