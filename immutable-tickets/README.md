# 🛡️ SST28-LLD101: Immutable Objects & Builder Pattern — Incident Ticket Exercise

Welcome to the Immutability and Builder Pattern exercise! This project demonstrates refactoring a leaky, mutable data model into a robust, thread-safe, and validated **Immutable** structure.

---

## 🏗️ Immutable Design Pattern
*An object whose state cannot be modified after it is created. Often paired with the **Builder** pattern for flexible construction.*

### **Exercise: Incident Ticket Refactoring**
- **🔍 Problem Before Refactoring**: The `IncidentTicket` class was highly unstable:
  1. **Public Setters** — Any part of the code could change a ticket's ID, priority, or reporter at any time, breaking audit trails.
  2. **List Leakage** — `getTags()` returned the internal `List` reference, allowing callers to bypass the class and modify tags directly.
  3. **Scattered Validation** — Validation was partially in `TicketService` and partially missing, allowing the creation of "half-valid" tickets (e.g., missing IDs or invalid emails).
  4. **Post-Creation Mutation** — `TicketService` would create a ticket and then call multiple setters to "configure" it, leading to inconsistent intermediate states.

- **💡 What is done (Solution)**: The ticket was converted to a strictly immutable class with a nested Builder that enforces all validation rules before the object is even born.
- **📝 Modified Existing Files**:
  - `IncidentTicket`: All fields marked **`final`**. All setters **removed**. Added a static inner **`Builder`** class. The `tags` list is now protected via `Collections.unmodifiableList()`. Added a `from(IncidentTicket)` static method to allow "updating" a ticket by creating a new one based on the old one.
  - `TicketService`: Refactored to use the Builder. Methods like `escalateToCritical()` and `assign()` no longer mutate the input ticket; instead, they return a **NEW** immutable instance with the updated fields.
  - `TryIt`: Updated to demonstrate that the original ticket remains unchanged after "updates" and that direct list modification now throws an exception.
- **✨ New Files Created & Their Purpose**:
  - `Validation`: A utility class created to centralize all business rules (email regex, ID format, non-blank checks), ensuring consistent validation across the entire app.

> 🎤 **SumUp:**
> *"In this exercise, `IncidentTicket` was a mess of mutability. You could change its ID after it was created, and anyone calling `getTags()` could sneakily add or remove tags because of a list leak. This makes the system impossible to debug or audit. I refactored it into a strictly Immutable class. I removed all setters and added a `Builder` that enforces strict validation — like checking email formats and ID lengths — before the ticket is created. I also protected the tags list using `unmodifiableList`. Now, if `TicketService` wants to 'assign' a ticket, it doesn't change the old one; it returns a brand new one. This makes the code thread-safe and ensures that once a ticket is created, its state is guaranteed to be valid and permanent."*
