package com.elevator.utils;

import com.elevator.entity.ElevatorRequest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CollectionUtils {

    public static ElevatorRequest pollRequestForUpDirection(List<ElevatorRequest> currentJobs) {

        Collections.sort(currentJobs, new Comparator<ElevatorRequest>() {
            @Override
            public int compare(ElevatorRequest o1, ElevatorRequest o2) {
                return o1.getInternalRequest().getDestinationFloor() - o2.getInternalRequest().getDestinationFloor();
            }
        });


        ElevatorRequest request = currentJobs.get(0);
        currentJobs.remove(0);
        return request;
    }
}
