# 🚀 SST28-LLD101: Low-Level Design Exercises

Solutions for the **[SST28-LLD101](https://github.com/kshitijmishra23/SST28-LLD101)** exercises, covering core design patterns and principles in Java.

> 📌 **Exercises source**: [github.com/kshitijmishra23/SST28-LLD101](https://github.com/kshitijmishra23/SST28-LLD101)

---

## 📂 Exercise Modules

| Module | Topic | Description |
|--------|-------|-------------|
| [`SOLID/`](./SOLID) | **SOLID Principles** | 10 refactoring exercises covering SRP, OCP, LSP, ISP, and DIP — each demonstrating how to identify and fix design violations. |
| [`singleton-metrics/`](./singleton-metrics) | **Singleton Pattern** | Fix a broken Singleton in a metrics registry system — covering thread safety, serialization, reflection guards, and correct usage. |
| [`flyweight-markers/`](./flyweight-markers) | **Flyweight Pattern** | Deduplicate map marker styles by extracting intrinsic state into shared immutable objects via a factory. |
| [`immutable-tickets/`](./immutable-tickets) | **Immutability & Builder** | Refactor a leaky, mutable data model into a robust, validated, and thread-safe immutable structure using the Builder pattern. |
| [`proxy-reports/`](./proxy-reports) | **Proxy Pattern** | Add Access Control and Lazy Loading (caching) to an expensive report loading system without modifying the original resource logic. |
| [`adapter-payments/`](./adapter-payments) | **Adapter Pattern** | Bridge two incompatible third-party payment SDKs behind a single `PaymentGateway` interface so `OrderService` is decoupled from all provider-specific logic. |
| [`pen-design/`](./pen-design) | **Pen Design (OOD)** | A comprehensive object-oriented design and implementation of a Pen system using Factory pattern and Composition. |
| [`snake-ladder/`](./snake-ladder) | **Snake & Ladder** | A complete $n \times n$ multiplayer Snake and Ladder game with randomized board generation and game engine logic. |
| [`parking-lot/`](./parking-lot) | **Parking Lot (OOD)** | A multilevel parking system with gate-based nearest slot assignment, vehicle compatibility logic, and polymorphic billing. |
| [`elevator-design/`](./elevator-design) | **Elevator System** | A multi-elevator control system with priority-based request handling, optimal load strategies, capacity management, and thread-safe operations. |
| [`bookmyshow/`](./bookmyshow) | **Movie Booking System** | A ticket booking system with theaters, shows, seat locking, coupon discounts, multiple payment methods, and dynamic pricing strategies. |

---

Each module has its own **README.md** with a detailed breakdown of the problems, solutions, modified/created files, and a SumUp for every exercise.
