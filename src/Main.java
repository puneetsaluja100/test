import com.elevator.ElevatorRequestExecutor;
import com.elevator.entity.Elevator;
import com.elevator.entity.ElevatorRequest;
import com.elevator.entity.ExternalRequest;
import com.elevator.entity.InternalRequest;
import com.elevator.enums.Direction;

public class Main {
    public static void main(String[] args) throws Exception {
        Elevator elevator = new Elevator();

        ExternalRequest er = new ExternalRequest(Direction.UP, 0);
        InternalRequest ir = new InternalRequest(5);
        ElevatorRequest request1 = new ElevatorRequest(ir, er);
        elevator.addJob(request1);

        ExternalRequest er2 = new ExternalRequest(Direction.UP, 2);
        InternalRequest ir2= new InternalRequest(4);
        ElevatorRequest request2 = new ElevatorRequest(ir2, er2);
        elevator.addJob(request2);


        ElevatorRequestExecutor elevatorRequestExecutor = new ElevatorRequestExecutor(elevator);
        Thread t2 = new Thread(elevatorRequestExecutor);
        t2.start();
    }
}








