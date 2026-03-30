# 🔌 SST28-LLD101: Adapter Pattern — Payments Exercise

Welcome to the Adapter Pattern exercise! This project demonstrates how to use the **Adapter** design pattern to bridge incompatible third-party payment SDK interfaces behind a single clean abstraction, so `OrderService` never needs to know which payment provider it's talking to.

---

## 🏗️ Adapter Design Pattern
*Convert the interface of a class into another interface that clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.*

### **Exercise: Payment Gateway Adapters**
- **🔍 Problem Before Refactoring**: `OrderService` directly depended on two incompatible third-party SDKs (`FastPayClient`, `SafeCashClient`). Each SDK had a completely different method signature:
  - `FastPayClient.payNow(custId, amountCents)` → returns a transaction string directly
  - `SafeCashClient.createPayment(amount, user)` → returns a `SafeCashPayment` object requiring `.confirm()`

  Any code that needed to support both providers had to contain provider-specific branching (`if provider == "fastpay" ... else if provider == "safecash" ...`), and adding a third provider meant editing that branching logic.

- **💡 What is done (Solution)**: A `PaymentGateway` target interface was introduced and two adapters were created — one per SDK — to translate the `charge(customerId, amountCents)` call into whatever the underlying SDK requires. `OrderService` now holds a `Map<String, PaymentGateway>` and never references either SDK directly. Adding a new provider only requires writing a new adapter class and registering it in `App`.

- **📝 Modified Existing Files**:
  - `App`: Uncommented and wired the two adapters — `FastPayAdapter` wrapping `FastPayClient`, and `SafeCashAdapter` wrapping `SafeCashClient` — into the registry map before passing it to `OrderService`.

- **✨ New Files Created & Their Purpose**:
  - `FastPayAdapter`: Implements `PaymentGateway`. Delegates to `FastPayClient.payNow(custId, amountCents)` and returns the transaction string directly.
  - `SafeCashAdapter`: Implements `PaymentGateway`. Delegates to `SafeCashClient.createPayment(amountCents, customerId)` and calls `.confirm()` on the returned `SafeCashPayment` object to produce the transaction string. Note the argument order difference (`amount` before `user`) — this is exactly the translation the adapter handles.

- **📦 Unchanged Files (Starter)**:
  - `PaymentGateway`: The target interface — `String charge(String customerId, int amountCents)`.
  - `OrderService`: Accepts a `Map<String, PaymentGateway>` via constructor; looks up the right gateway by provider key and delegates. Zero provider-specific logic.
  - `FastPayClient`, `SafeCashClient`, `SafeCashPayment`: Third-party SDK stubs — untouched, as the Adapter pattern requires.

> 🎤 **SumUp:**
> *"In this exercise, `OrderService` needed to support two payment providers — FastPay and SafeCash — but both SDKs had completely different APIs. FastPay takes custId first and returns a string directly. SafeCash takes amount first and user second, returns a payment object that needs `.confirm()`. Without adapters, any integration code would need to know these differences and branch on the provider name. I introduced a `PaymentGateway` target interface with a single `charge(customerId, amountCents)` method, then created `FastPayAdapter` and `SafeCashAdapter` — each translating the uniform interface call into whatever the SDK underneath expects. `OrderService` now only ever calls `gateway.charge(...)` and has no knowledge of either SDK. To add a third provider like Stripe tomorrow, you just write a `StripeAdapter` and register it in `App` — `OrderService` requires zero changes."*
