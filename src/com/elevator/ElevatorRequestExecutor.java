package com.elevator;

import com.elevator.entity.Elevator;

public class ElevatorRequestExecutor implements Runnable {
    private Elevator elevator;

    public ElevatorRequestExecutor(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        elevator.startElevator();
    }
}
