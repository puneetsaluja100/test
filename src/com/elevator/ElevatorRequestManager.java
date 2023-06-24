package com.elevator;

import com.elevator.entity.Elevator;
import com.elevator.entity.ElevatorRequest;

import java.util.List;

public class ElevatorRequestManager {

    List<Elevator> elevators;

    static int counter = 0;

    public void placeRequest(ElevatorRequest elevatorRequest) {
        //handles request in round robin fashion
        elevators.get(counter% elevators.size()).addJob(elevatorRequest);
        counter++;
    }

    public ElevatorRequestManager(List<Elevator> elevators) {
        this.elevators = elevators;
    }
}
