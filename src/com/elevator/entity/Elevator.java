package com.elevator.entity;

import com.elevator.enums.Direction;
import com.elevator.enums.ElevatorState;
import com.elevator.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator {
    private int elevatorId = 1;
    private int currentFloor = 0;
    private Direction direction = Direction.UP;
    private ElevatorState state = ElevatorState.IDLE;

    private List<ElevatorRequest> currentJobs = new ArrayList<>();

    private List<ElevatorRequest> upPendingJobs = new ArrayList<>();

    private List<ElevatorRequest> downPendingJobs = new ArrayList<>();

    public void startElevator() {
        while (true) {
            if (!currentJobs.isEmpty()) {
                if (direction == Direction.UP) {
                    ElevatorRequest request = pollRequestForUpDirection();
                    processUpRequest(request);
                    if (currentJobs.isEmpty()) {
                        addPendingDownJobsToCurrentJobs();
                    }
                }
                if (direction == Direction.DOWN) {
                    ElevatorRequest request = pollRequestForDownDirection();
                    processDownRequest(request);
                    if (currentJobs.isEmpty()) {
                        addPendingUpJobsToCurrentJobs();
                    }

                }
            }
        }
    }

    private void processUpRequest(ElevatorRequest request) {
        int startFloor = currentFloor;
        if (startFloor < request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor; i <= request.getExternalRequest().getSourceFloor(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Elevator reached on floor -- " + i);
                currentFloor = i;
            }
        }

        System.out.println("Reached Requested source Floor--opening door");

        startFloor = currentFloor;

        for (int i = startFloor; i <= request.getInternalRequest().getDestinationFloor(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Elevator reached destination floor -- " + i);
            currentFloor = i;
            if (checkIfNewJobCanBeProcessed(request)) {
                break;
            }
        }

    }

    private void processDownRequest(ElevatorRequest request) {

        int startFloor = currentFloor;
        if (startFloor < request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor; i <= request.getExternalRequest().getSourceFloor(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Elevator reached on floor -- " + i);
                currentFloor = i;
            }
        }

        System.out.println("Reached Requested source Floor--opening door");

        startFloor = currentFloor;

        for (int i = startFloor; i >= request.getInternalRequest().getDestinationFloor(); i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Elevator reached destination floor -- " + i);
            currentFloor = i;
            if (checkIfNewJobCanBeProcessed(request)) {
                break;
            }
        }

    }

    private ElevatorRequest pollRequestForUpDirection() {
        return CollectionUtils.pollRequestForUpDirection(currentJobs);
    }

    private ElevatorRequest pollRequestForDownDirection() {
        return CollectionUtils.pollRequestForDownDirection(currentJobs);
    }

    private boolean checkIfNewJobCanBeProcessed(ElevatorRequest currentRequest) {
        if (!currentJobs.isEmpty()) {
            if (direction == Direction.UP) {
                ElevatorRequest request = pollRequestForUpDirection();
                if (request.getInternalRequest().getDestinationFloor() < currentRequest.getInternalRequest()
                        .getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);

            }

            if (direction == Direction.DOWN) {
                ElevatorRequest request = pollRequestForDownDirection();
                if (request.getInternalRequest().getDestinationFloor() > currentRequest.getInternalRequest()
                        .getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);

            }

        }
        return false;

    }


    public void addJob(ElevatorRequest request) {
        System.out.println("Lift is currently on floor : "+ currentFloor + " state: "+state);
        if (state == ElevatorState.IDLE) {
            state = ElevatorState.MOVING;
            direction = request.getExternalRequest().getDirection();
            currentJobs.add(request);
        } else if (state == ElevatorState.MOVING) {

            if (request.getExternalRequest().getDirection() != direction) {
                addtoPendingJobs(request);
            } else if (request.getExternalRequest().getDirection() == direction) {
                if (direction == Direction.UP
                        && request.getInternalRequest().getDestinationFloor() < currentFloor) {
                    addtoPendingJobs(request);
                } else if (direction == Direction.DOWN
                        && request.getInternalRequest().getDestinationFloor() > currentFloor) {
                    addtoPendingJobs(request);
                } else {
                    currentJobs.add(request);
                }

            }

        }

    }

    public void addtoPendingJobs(ElevatorRequest request) {
        if (request.getExternalRequest().getDirection() == Direction.UP) {
            System.out.println("Add to pending up jobs");
            upPendingJobs.add(request);
        } else {
            System.out.println("Add to pending down jobs");
            downPendingJobs.add(request);
        }
    }

    private void addPendingDownJobsToCurrentJobs() {
        if (!downPendingJobs.isEmpty()) {
            currentJobs = downPendingJobs;
            direction = Direction.DOWN;
        } else {
            state = ElevatorState.IDLE;
        }

    }

    private void addPendingUpJobsToCurrentJobs() {
        if (!upPendingJobs.isEmpty()) {
            currentJobs = upPendingJobs;
            direction = Direction.UP;
        } else {
            state = ElevatorState.IDLE;
        }

    }



    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }
}
