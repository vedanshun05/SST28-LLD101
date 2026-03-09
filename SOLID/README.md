# 🚀 SST28-LLD101: SOLID Principles Refactoring Exercises

Welcome to the SOLID Principles Refactoring Exercises repository! This project contains a series of refactoring exercises demonstrating the first three principles of **SOLID**: 
- **S**ingle Responsibility Principle (SRP)
- **O**pen/Closed Principle (OCP)
- **L**iskov Substitution Principle (LSP)

Below you'll find a comprehensive breakdown of the initial design problems, my architectural solutions, the specific files modified or created, and SumUp for each exercise.

---

## 🏗️ 1. Single Responsibility Principle (SRP)
*A class should have only one reason to change, meaning it should have only one job or responsibility.*

### **Exercise 1: Student Onboarding System**
- **🔍 Problem Before Refactoring**: A monolithic design where a single class handled everything: parsing user input, validating data, generating IDs, saving to a database, and printing outputs.
- **💡 What is done (Solution)**: Responsibilities were extracted from the monolith into highly cohesive classes.
- **📝 Modified Existing Files**:
  - `OnboardingService`: Modified to remove all low-level logic (parsing, validating, saving, printing). Its new purpose is simply to orchestrate the workflow by delegating tasks to the new specialized classes.
- **✨ New Files Created & Their Purpose**:
  - `InputParser`: Created to parse raw input strings into key-value maps.
  - `StudentValidator`: Created to strictly handle the validation of student input data.
  - `IdUtil`: Created to isolate the generation of unique student IDs.
  - `StudentRepository` & `FakeDb`: Created to separate data persistence logic from business logic.
  - `OnboardingPrinter`: Created to handle all console output, removing display logic from the core service.

> 🎤 **SumUp (Ex 1):**
> *"In Exercise 1, we started with a massive 'God Class' that did everything from parsing to saving database records. The problem is that if our database changes or our validation rules change, we are editing the exact same file. My solution was to break this up based on the Single Responsibility Principle. I created `InputParser` for parsing, `StudentValidator` for validation, and dedicated classes for printing and storage. Now, `OnboardingService` acts purely as an orchestrator, making the system highly modular and easy to test."*

### **Exercise 2: Cafeteria Billing System**
- **🔍 Problem Before Refactoring**: A single class likely handled adding menu items, calculating order totals, applying taxes/discounts, formatting invoices, and saving them.
- **💡 What is done (Solution)**: The code was refactored to separate calculations, storage, and presentation.
- **📝 Modified Existing Files**:
  - `CafeteriaSystem`: Stripped of direct calculation, formatting, and saving logic. It now only acts as the system facade or orchestrator.
- **✨ New Files Created & Their Purpose**:
  - `OrderCalculator`: Created to focus purely on summing up the order totals.
  - `TaxRules` & `DiscountRules`: Created to hold the specific business logic for calculating taxes and applying discounts.
  - `InvoiceFormatter`: Created specifically for converting invoice data into a displayable string format.
  - `InvoiceRepository` & `FileStore`: Created to handle the storage of invoices to files, abstracting away IO operations.

> 🎤 **SumUp (Ex 2):**
> *"Similarly, in Exercise 2, the Cafeteria system was doing too much. Calculating taxes, formatting a receipt, and saving it to a file are completely different reasons to change. To fix this, I separated the calculation math into `OrderCalculator` and `TaxRules`/`DiscountRules`. Then, I completely separated the display formatting into an `InvoiceFormatter` and storage into an `InvoiceRepository`. Now, if we need to change how receipts look, the calculation logic remains untouched."*

---

## 🚪 2. Open/Closed Principle (OCP)
*Software entities should be open for extension, but closed for modification. You should be able to add new behavior without editing existing code.*

### **Exercise 3: Placement Eligibility Engine**
- **🔍 Problem Before Refactoring**: The eligibility engine (`EligibilityEngine`) used large `if-else` or `switch` statements to verify various criteria (attendance, CGR, etc.). Adding a new rule required modifying the engine itself.
- **💡 What is done (Solution)**: The system introduces an abstraction for the rules.
- **📝 Modified Existing Files**:
  - `EligibilityEngine`: Modifed to remove all hardcoded rule checks. It now accepts a list of `EligibilityRule` implementations and simply iterates over them to determine eligibility. It is now "closed" for modification.
- **✨ New Files Created & Their Purpose**:
  - `EligibilityRule`: The rule interface created as an abstraction to evaluate student eligibility.
  - `AttendanceRule`, `CgrRule`, `CreditsRule`, `DisciplinaryFlagRule`: Created to encapsulate each specific rule's logic into its own class. New rules can now be added by creating new files like these without modifying existing code.

> 🎤 **SumUp (Ex 3):**
> *"Moving to the Open/Closed Principle, Exercise 3 had a sprawling `if-else` chain verifying student eligibility. If the college adds a new rule, we'd have to modify the core engine, risking bugs. My solution was to introduce an `EligibilityRule` interface and create separate classes for `AttendanceRule`, `CgrRule`, etc. The engine now just loops through a list of these rules. If we want to add a new rule tomorrow, we simply write a new class; the engine is closed for modification but open for extension."*

### **Exercise 4: Hostel Fee Calculator**
- **🔍 Problem Before Refactoring**: Calculating prices for room types and add-ons (laundry, mess, gym) was hardcoded conditionally inside the main calculation class.
- **💡 What is done (Solution)**: Abstractions were introduced to fetch prices, separating pricing mappings from calculation logic.
- **📝 Modified Existing Files**:
  - `HostelFeeCalculator`: Modified to remove the hardcoded price lookups (the `if/else` statements). It now depends purely on injected pricers, and simply sums whatever they return.
- **✨ New Files Created & Their Purpose**:
  - `RoomPricer` & `AddOnPricer`: Interfaces created to abstract the concept of fetching prices.
  - `MapRoomPricer` & `MapAddOnPricer`: Implementations created mapping specific room types/add-ons to their prices. Adding new add-ons only requires extending these mappings without touching the calculator logic.

> 🎤 **SumUp (Ex 4):**
> *"In Exercise 4, computing hostel fees relied on hardcoding prices for 'Mess', 'Laundry', or different room types directly in the calculation logic. This is bad because prices change often! I fixed this by splitting out the pricing lookup into abstracts: `RoomPricer` and `AddOnPricer`. The calculator now takes in these pricers and calculates the total. We can now update our pricing data mappings (like `MapAddOnPricer`) without ever touching the calculator class."*

---

## 🎭 3. Liskov Substitution Principle (LSP)
*Subtypes must be substitutable for their base types without altering the correctness of the program or throwing unexpected exceptions.*

### **Exercise 5: Document Exporter**
- **🔍 Problem Before Refactoring**: A broad `export()` method on a base class meant a subclass like `PdfExporter` might throw unexpected runtime exceptions if called with invalid data (e.g., text > 20 chars), breaking callers who expected it to behave like the base `Exporter`.
- **💡 What is done (Solution)**: A pre-condition check was introduced to the abstraction to ensure safely substitutable behavior.
- **📝 Modified Existing Files**:
  - `Exporter`: Modified to add a `supports(ExportRequest req)` abstract method so callers can safely verify if an exporter can handle a request.
- **✨ New Files Created & Their Purpose**:
  - `PdfExporter` (or similar derived classes): Evaluated via the newly available `supports()` method. By returning `false` for unsupported input rather than throwing an unexpected error inside `export()`, it protects the program from crashing and adheres to LSP.

> 🎤 **SumUp (Ex 5):**
> *"For the Liskov Substitution Principle in Exercise 5, we had an `Exporter` abstraction. The problem was that the `PdfExporter` couldn't handle text longer than 20 characters, so it was just throwing a runtime exception. If a generic caller passed a long string, the program would crash, violating LSP. I solved this by adding a `supports()` pre-check method to the base `Exporter`. Now, the caller can ask `supports()` first. `PdfExporter` can politely return 'false' instead of blowing up, keeping everything safe and substitutable."*

### **Exercise 6: Notification Sender**
- **🔍 Problem Before Refactoring**: Subclasses (like `WhatsAppSender`) had specific format requirements for inputs (like phone numbers needing to start with `+`). Substituting `WhatsAppSender` where a standard `NotificationSender` was expected resulted in run-time crashes on invalid phone numbers.
- **💡 What is done (Solution)**: The same structural pre-check approach was applied.
- **📝 Modified Existing Files**:
  - `NotificationSender`: Modified to include the `supports(Notification n)` abstract method, which prevents throwing runtime exceptions that break substitutability.
  - `Demo06`: Fixed to apply the `supports()` guard **consistently to all senders** — `email`, `sms`, and `wa` — before calling `send()`. Previously only `WhatsAppSender` was guarded; `email.send(n)` and `sms.send(n)` were called directly without a check. This meant if `n.phone` were `null`, `SmsSender` would throw `IllegalArgumentException`, violating the base contract. Now all three senders are treated uniformly.
  - `WhatsAppSender`: Gracefully rejects invalid data by returning `false` from `supports()`.

> 🎤 **SumUp (Ex 6):**
> *"Exercise 6 dealt with notification senders where `WhatsAppSender` strictly needed phone numbers structured with a `+` symbol. Without a way to check this beforehand, treating all senders equally led to crashes when passing a local number to the WhatsApp sender. I implemented a `supports()` method on `NotificationSender` so each subtype can declare what it accepts. But there was a second issue: the original `Demo06` only applied the `supports()` guard to `WhatsAppSender` — `email.send()` and `sms.send()` were called directly without checking. This is inconsistent: if `SmsSender` could throw and `EmailSender` couldn't handle a null email, the base contract is broken. The fix was to guard all three senders with `if (sender.supports(n))` before calling `send()`, making every subtype uniformly substitutable without unexpected exceptions."* 

---

## 🔌 4. Interface Segregation Principle (ISP)
*Clients should not be forced to depend on interfaces they do not use. Prefer small, client-specific interfaces over "fat" ones.*

### **Exercise 7: Smart Classroom System**
- **🔍 Problem Before Refactoring**: A single fat interface `SmartClassroomDevice` declared methods for power control, brightness, temperature, attendance scanning, and input connection. Every device — `Projector`, `LightsPanel`, `AirConditioner`, `AttendanceScanner` — was forced to implement all of these, filling unused methods with dummy/no-op logic.
- **💡 What is done (Solution)**: The fat interface was split into fine-grained, capability-based interfaces so each device only implements what it actually supports.
- **📝 Modified Existing Files**:
  - `Projector`: Now implements only `Powerable` and `InputConnectable`. All dummy methods removed.
  - `LightsPanel`: Now implements only `Powerable` and `BrightnessControllable`.
  - `AirConditioner`: Now implements only `Powerable` and `TemperatureControllable`.
  - `AttendanceScanner`: Now implements only `Scannable`.
  - `DeviceRegistry`: Changed from storing `SmartClassroomDevice` to storing `Object`, with a generic `getFirst(Class<T>)` method to query devices by capability interface.
  - `ClassroomController`: Refactored to request devices by capability type (e.g., `reg.getFirst(BrightnessControllable.class)`) instead of by class name string.
- **✨ New Files Created & Their Purpose**:
  - `Powerable`: Interface for `powerOn()` / `powerOff()`.
  - `BrightnessControllable`: Interface for `setBrightness(int pct)`.
  - `TemperatureControllable`: Interface for `setTemperatureC(int c)`.
  - `Scannable`: Interface for `scanAttendance()`.
  - `InputConnectable`: Interface for `connectInput(String port)`.
- **🗑️ Deleted Files**:
  - `SmartClassroomDevice`: The original fat interface, replaced by the 5 capability interfaces above.

> 🎤 **SumUp (Ex 7):**
> *"In Exercise 7, there was one massive `SmartClassroomDevice` interface that forced every device to implement methods like `setBrightness`, `setTemperatureC`, `scanAttendance`, and `connectInput` — even when those methods were completely irrelevant. The `Projector` had a dummy `scanAttendance()` returning 0, the `AirConditioner` had a no-op `setBrightness()`, and so on. My fix was to split this fat interface into five small, capability-based interfaces: `Powerable`, `BrightnessControllable`, `TemperatureControllable`, `Scannable`, and `InputConnectable`. Now the `Projector` only implements `Powerable` and `InputConnectable`, the `AttendanceScanner` only implements `Scannable`, etc. The `DeviceRegistry` was updated to query by capability type, so the controller asks for exactly the interface it needs."*

### **Exercise 8: Club Management System**
- **🔍 Problem Before Refactoring**: A single fat interface `ClubAdminTools` combined finance operations (`addIncome`, `addExpense`), minutes management (`addMinutes`), and event planning (`createEvent`, `getEventsCount`). Each role tool (`TreasurerTool`, `SecretaryTool`, `EventLeadTool`) was forced to implement all methods, filling irrelevant ones with dummy logic.
- **💡 What is done (Solution)**: The fat interface was split into three role-specific interfaces aligned to what each tool actually needs.
- **📝 Modified Existing Files**:
  - `TreasurerTool`: Now implements only `FinanceTool`. All dummy `addMinutes`, `createEvent`, `getEventsCount` removed.
  - `SecretaryTool`: Now implements only `MinutesTool`.
  - `EventLeadTool`: Now implements only `EventTool`.
  - `ClubConsole`: Uses narrow interface types (`FinanceTool`, `MinutesTool`, `EventTool`) instead of the fat `ClubAdminTools`.
- **✨ New Files Created & Their Purpose**:
  - `FinanceTool`: Interface for `addIncome()` and `addExpense()`.
  - `MinutesTool`: Interface for `addMinutes()`.
  - `EventTool`: Interface for `createEvent()` and `getEventsCount()`.
- **🗑️ Deleted Files**:
  - `ClubAdminTools`: The original fat interface, replaced by the 3 role-specific interfaces above.

> 🎤 **SumUp (Ex 8):**
> *"Exercise 8 had the same ISP problem but in a club management context. `ClubAdminTools` was a fat interface that combined finance, minutes, and event methods — but the `TreasurerTool` only cares about money, the `SecretaryTool` only cares about meeting minutes, and the `EventLeadTool` only cares about events. Each was forced to implement irrelevant dummy methods. I split this into three focused interfaces: `FinanceTool`, `MinutesTool`, and `EventTool`. Now each role tool implements only the interface relevant to it, and the `ClubConsole` references the narrow types. If we add a new role tomorrow like 'PublicityLead', it just implements its own interface without being burdened by finance or minutes methods."*

---

## 🔄 5. Dependency Inversion Principle (DIP)
*High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details; details should depend on abstractions.*

### **Exercise 9: Code Evaluation System**
- **🔍 Problem Before Refactoring**: `EvaluationPipeline.evaluate()` directly instantiated concrete classes (`PlagiarismChecker`, `CodeGrader`, `ReportWriter`, `Rubric`) with `new` inside the method. The high-level pipeline was tightly coupled to low-level implementations, making it impossible to test or swap components without editing the pipeline.
- **💡 What is done (Solution)**: Abstractions were introduced for each component, and all dependencies are injected via the pipeline's constructor.
- **📝 Modified Existing Files**:
  - `EvaluationPipeline`: Removed all `new` calls. Now accepts `Checker`, `Grader`, `Writer`, and `Rubric` via constructor injection. The `evaluate()` method works entirely through abstractions.
  - `PlagiarismChecker`: Now implements the `Checker` interface.
  - `CodeGrader`: Now implements the `Grader` interface.
  - `ReportWriter`: Now implements the `Writer` interface.
  - `Main`: Updated to create concrete instances and inject them into the pipeline.
- **✨ New Files Created & Their Purpose**:
  - `Checker`: Interface abstracting plagiarism checking — `int check(Submission s)`.
  - `Grader`: Interface abstracting code grading — `int grade(Submission s, Rubric r)`.
  - `Writer`: Interface abstracting report writing — `String write(Submission s, int plag, int code)`.

> 🎤 **SumUp (Ex 9):**
> *"In Exercise 9, the `EvaluationPipeline` was doing `new PlagiarismChecker()`, `new CodeGrader()`, `new ReportWriter()` directly inside its `evaluate()` method. This means the high-level pipeline is tightly coupled to these exact concrete classes. If we want to swap in a different grading strategy or a mock checker for testing, we have to modify the pipeline itself. I fixed this by introducing three interfaces — `Checker`, `Grader`, and `Writer` — and injecting them through the constructor. The concrete classes now implement these interfaces, and `Main` does the wiring. The pipeline is now completely decoupled; we can easily swap in test doubles or alternative implementations without touching pipeline code."*

### **Exercise 10: Transport Booking System**
- **🔍 Problem Before Refactoring**: `TransportBookingService.book()` directly instantiated `DistanceCalculator`, `DriverAllocator`, and `PaymentGateway` with `new`. Pricing logic (fare calculation) was also embedded directly in the booking method, mixing business rules with infrastructure.
- **💡 What is done (Solution)**: Abstractions were introduced for each service, pricing logic was extracted, and all dependencies are injected via constructor.
- **📝 Modified Existing Files**:
  - `TransportBookingService`: Removed all `new` calls. Now accepts `DistanceService`, `AllocationService`, `PaymentService`, and `FareCalculator` via constructor. The `book()` method works through abstractions.
  - `DistanceCalculator`: Now implements the `DistanceService` interface.
  - `DriverAllocator`: Now implements the `AllocationService` interface.
  - `PaymentGateway`: Now implements the `PaymentService` interface.
  - `Main`: Updated to create concrete instances and inject them into the booking service.
- **✨ New Files Created & Their Purpose**:
  - `DistanceService`: Interface abstracting distance calculation — `double km(GeoPoint a, GeoPoint b)`.
  - `AllocationService`: Interface abstracting driver allocation — `String allocate(String studentId)`.
  - `PaymentService`: Interface abstracting payment — `String charge(String studentId, double amount)`.
  - `FareCalculator`: Extracted class encapsulating fare pricing logic — `double calculate(double km)`.

> 🎤 **SumUp (Ex 10):**
> *"Finally, Exercise 10 had the same DIP violation in a transport booking context. `TransportBookingService` was creating `new DistanceCalculator()`, `new DriverAllocator()`, and `new PaymentGateway()` inside the `book()` method, plus the fare calculation formula was hardcoded inline. I introduced `DistanceService`, `AllocationService`, and `PaymentService` interfaces, extracted the fare formula into a `FareCalculator` class, and injected everything via constructor. Now if we want to add a 'mock' allocator for tests or swap to a different payment provider, we just pass in a different implementation — the booking service itself never changes."*
