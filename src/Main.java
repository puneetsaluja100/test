import com.elevator.ElevatorRequestExecutor;
import com.elevator.ElevatorRequestManager;
import com.elevator.entity.*;
import com.elevator.enums.Direction;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Building building = new Building();
        building.setFloors(Arrays.asList(new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5)));
        building.setElevators(getElevators());

        for(Elevator e: building.getElevators()) {
            ElevatorRequestExecutor elevatorRequestExecutor = new ElevatorRequestExecutor(e);
            Thread t2 = new Thread(elevatorRequestExecutor);
            t2.start();
        }




        ExternalRequest er = new ExternalRequest(Direction.UP, 0);
        InternalRequest ir = new InternalRequest(5);
        ElevatorRequest request1 = new ElevatorRequest(ir, er);


        ExternalRequest er2 = new ExternalRequest(Direction.UP, 2);
        InternalRequest ir2= new InternalRequest(4);
        ElevatorRequest request2 = new ElevatorRequest(ir2, er2);



        ExternalRequest er3 = new ExternalRequest(Direction.DOWN, 0);
        InternalRequest ir3= new InternalRequest(4);
        ElevatorRequest request3 = new ElevatorRequest(ir3, er3);


        ElevatorRequestManager elevatorRequestManager = new ElevatorRequestManager(building.getElevators());
        elevatorRequestManager.placeRequest(request1);
        elevatorRequestManager.placeRequest(request2);
        elevatorRequestManager.placeRequest(request3);



    }

    public static List<Elevator> getElevators() {
        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        Elevator elevator3 = new Elevator(3);

        return Arrays.asList(elevator1, elevator2, elevator3);
    }
}








