import com.elevator.ElevatorRequestExecutor;
import com.elevator.ElevatorRequestManager;
import com.elevator.entity.*;
import com.elevator.enums.Direction;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //Considering building with 5 floors and 3 elevators
        Building building = new Building();
        building.setFloors(Arrays.asList(new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5)));
        building.setElevators(getElevators());

        for(Elevator e: building.getElevators()) {
            ElevatorRequestExecutor elevatorRequestExecutor = new ElevatorRequestExecutor(e);
            Thread t2 = new Thread(elevatorRequestExecutor);
            t2.start();
        }


        ButtonPanel buttonPanel = new ButtonPanel(building);
        buttonPanel.acceptUserRequest(0,5, Direction.UP);
        buttonPanel.acceptUserRequest(2,4, Direction.UP);
        buttonPanel.acceptUserRequest(0,4, Direction.DOWN);
    }

    public static List<Elevator> getElevators() {
        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        Elevator elevator3 = new Elevator(3);

        return Arrays.asList(elevator1, elevator2, elevator3);
    }
}








