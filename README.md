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
  - `NotificationSender`: Modified to include the `supports(Notification n)` abstract method. Which prevents throwing runtime exceptions that break substitutability.
- **✨ New Files Created & Their Purpose**:
  - `WhatsAppSender`: Modified/Created to gracefully reject invalid data by returning `false` from `supports()`. Callers (like a dispatch loop) can now iterate through senders and only call `send()` if `supports()` returns true, ensuring perfect polymorphic substitutability without exceptions.

> 🎤 **SumUp (Ex 6):**
> *"Finally, Exercise 6 dealt with notification senders where `WhatsAppSender` strictly needed phone numbers structured with a `+` symbol. Without a way to check this beforehand, treating all senders equally led to crashes when passing a local number to the WhatsApp sender. I implemented the exact same pattern: I added `supports()` to `NotificationSender`. The dispatch system now loops through all notification senders and asks 'do you support this format?' before calling `send()`, meaning we no longer get unexpected crashes and we fully respect LSP."* 
