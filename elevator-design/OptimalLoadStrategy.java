import java.util.List;

public class OptimalLoadStrategy implements ElevatorSelectionStrategy {
    
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, ElevatorRequest request) {
        ElevatorCar bestElevator = null;
        int minScore = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (elevator.getStatus() == ElevatorStatus.MAINTENANCE || 
                elevator.getStatus() == ElevatorStatus.EMERGENCY) {
                continue;
            }

            if (!elevator.canAccommodate(request.getPassengersCount())) {
                continue;
            }

            int score = calculateScore(elevator, request);
            
            if (score < minScore) {
                minScore = score;
                bestElevator = elevator;
            }
        }

        return bestElevator != null ? bestElevator : elevators.get(0);
    }

    private int calculateScore(ElevatorCar elevator, ElevatorRequest request) {
        int score = 0;
        int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
        
        score += distance * 10;

        if (elevator.getStatus() == ElevatorStatus.IDLE) {
            score -= 50;
        } else if (elevator.getCurrentDirection() == request.getDirection()) {
            if (request.getDirection() == Direction.UP && 
                elevator.getCurrentFloor() <= request.getSourceFloor()) {
                score -= 20;
            } else if (request.getDirection() == Direction.DOWN && 
                       elevator.getCurrentFloor() >= request.getSourceFloor()) {
                score -= 20;
            } else {
                score += 30;
            }
        } else {
            score += 50;
        }

        int loadFactor = (elevator.getCurrentPassengers() * 100) / 
                        (elevator.getCurrentPassengers() + elevator.getAvailableCapacity());
        score += loadFactor / 2;

        return score;
    }
}
