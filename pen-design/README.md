# 🖊️ Pen Design Implementation

## 🚀 Implementation
The system is implemented using the **Factory Pattern** and **Composition**. Below is the core file structure:

- `Pen.java`: Interface defining the contract.
- `AbstractPen.java`: Abstract base class for state management.
- `BallpointPen.java`, `FountainPen.java`, `GelPen.java`: Concrete implementations.
- `InkCartridge.java`: Managed via composition for ink tracking.
- `PenFactory.java`: Creational logic.
- `Color.java`, `PenType.java`: Type-safe enums.
- `Demo.java`: Test harness for verification.

## 📊 Class Diagram
*I will manually draw the class diagram and attach it here.*
