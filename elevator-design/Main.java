public class Main {
    
    public static void main(String[] args) {
        ElevatorSystemService system = ElevatorSystemService.getInstance(
            "Tech Tower", 
            10,  // 10 floors
            3,   // 3 elevators
            8    // capacity of 8 passengers each
        );

        System.out.println("\n🎬 Starting Elevator System Demo...\n");

        System.out.println("📍 Scenario 1: Person at floor 0 wants to go to floor 5");
        system.requestElevator(0, 5, 1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n📍 Scenario 2: Group of 3 at floor 3 wants to go to floor 8");
        system.requestElevator(3, 8, 3);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n📍 Scenario 3: Emergency request - 2 people at floor 9 to ground floor");
        system.requestElevator(9, 0, 2, RequestPriority.EMERGENCY);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n📍 Scenario 4: Regular request - 4 people at floor 2 to floor 7");
        system.requestElevator(2, 7, 4);

        system.displaySystemStatus();

        System.out.println("\n✅ Demo completed!");
    }
}
