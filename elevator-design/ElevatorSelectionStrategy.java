import java.util.List;

public interface ElevatorSelectionStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, ElevatorRequest request);
}
