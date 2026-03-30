# 🛗 Elevator Design System

## 🚀 Implementation
A multi-elevator control system with priority-based request handling and optimal load strategies. Below is the file structure:

- `ElevatorSystemService.java`: Singleton service providing system-level API.
- `ElevatorController.java`: Thread-safe request coordination using ReentrantLock and PriorityQueue.
- `ElevatorCar.java`: Core elevator logic with movement, capacity management, and destination queues.
- `Door.java`: Door state management with simulated operations.
- `ElevatorRequest.java`: Request with priority, timestamp, and passenger count.
- `Building.java`, `Floor.java`: Building and floor abstractions.
- `ElevatorSelectionStrategy.java`: Strategy interface for elevator selection.
- `OptimalLoadStrategy.java`: Multi-factor scoring algorithm for elevator selection.
- `Direction.java`, `ElevatorStatus.java`, `DoorState.java`, `RequestPriority.java`: Enums for states and priorities.
- `Main.java`: Demonstration with different scenarios.

## 📊 Class Diagram
*I will manually draw the class diagram and attach it here.*
