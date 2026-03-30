# 🔒 SST28-LLD101: Singleton Pattern — Metrics Registry Exercise

Welcome to the Singleton Pattern exercise! This project demonstrates identifying and fixing a **broken Singleton** implementation in a metrics registry system, covering thread safety, serialization, reflection, and correct usage patterns.

---

## 🏗️ Singleton Design Pattern
*Ensure a class has only one instance and provide a global point of access to it.*

### **Exercise: MetricsRegistry Singleton**
- **🔍 Problem Before Refactoring**: The `MetricsRegistry` class was intended to be a Singleton but had multiple critical flaws:
  1. **Public Constructor** — Anyone could call `new MetricsRegistry()`, bypassing the singleton and creating multiple instances.
  2. **Racy Lazy Initialization** — `getInstance()` had no synchronization; two threads calling it simultaneously could each create a separate instance.
  3. **No Serialization Protection** — The class implemented `Serializable` but lacked a `readResolve()` method, meaning deserialization would silently create a new instance.
  4. **No Reflection Guard** — Nothing prevented reflective access from breaking the singleton contract.
  5. **Incorrect Usage in `MetricsLoader`** — `MetricsLoader.loadFromFile()` directly called `new MetricsRegistry()` instead of using the global singleton instance, creating a disconnected local copy.
  6. **Incorrect Usage in `App`** — `App.main()` also called `new MetricsRegistry()` directly for `r3`, demonstrating the broken constructor visibility.

- **💡 What is done (Solution)**: The singleton was hardened with four layers of protection, the public API was completed, all consumers were fixed to use `getInstance()`, and demo/verification programs were added for each protection layer.

- **📝 Modified Existing Files**:
  - `MetricsRegistry`: Constructor made **private**. Instance field marked **`volatile`**. `getInstance()` now uses **double-checked locking** with `synchronized`. A **reflection guard** throws `IllegalStateException` if the constructor is invoked when an instance already exists. A **`readResolve()`** method ensures deserialization returns the existing singleton. Added **`increment(key)`** (thread-safe atomic increment via `ConcurrentHashMap.merge`) and **`getAll()`** (returns an immutable snapshot) to complete the required public API.
  - `MetricsLoader`: Replaced `new MetricsRegistry()` → `MetricsRegistry.getInstance()` so loaded metrics populate the global singleton instead of a throwaway local copy.
  - `App`: Updated to demonstrate `increment(key)` and `getAll()` instead of `setCount()`, correctly showing counter accumulation across shared references.
  - `ConcurrencyCheck`: **No changes needed** — it already used `getInstance()`. With the thread-safety fix, it now correctly reports **1 unique instance** across all 80 concurrent threads.

- **✨ New Files Created & Their Purpose**:
  - `ReflectionAttack`: Demonstrates that accessing the private constructor via `getDeclaredConstructor().setAccessible(true)` is blocked — the constructor guard throws `IllegalStateException`, preventing a second instance.
  - `SerializationCheck`: Demonstrates that serializing and deserializing the singleton via `ObjectOutputStream` / `ObjectInputStream` returns the exact same instance (verified by `==` and `identityHashCode`), thanks to `readResolve()`.

> 🎤 **SumUp:**
> *"In this exercise, the `MetricsRegistry` was supposed to be a Singleton but was broken in almost every way possible. The constructor was public, so anyone could call `new MetricsRegistry()`. The lazy initialization in `getInstance()` had a classic race condition — two threads could slip through the `null` check simultaneously and create two instances. On top of that, it implemented `Serializable` without `readResolve()`, so deserializing would also create a duplicate. And the public API was incomplete — `increment(key)` and `getAll()` were missing, which are the core operations for a metrics registry. I fixed all of this: the constructor is now private with a reflection guard, `getInstance()` uses volatile + double-checked locking for thread safety, `readResolve()` ensures deserialization returns the same instance, and the API now has `increment()` (using `ConcurrentHashMap.merge` for atomicity) and `getAll()` (returning an immutable snapshot). I also added `ReflectionAttack.java` and `SerializationCheck.java` to verify each protection layer in isolation."*
