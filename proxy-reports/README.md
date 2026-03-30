# 🎭 SST28-LLD101: Proxy Design Pattern — CampusVault Report System

Welcome to the Proxy Pattern exercise! This project demonstrates adding **Access Control** and **Lazy Loading** to an expensive resource without modifying its core logic.

---

## 🏗️ Proxy Design Pattern
*Provide a surrogate or placeholder for another object to control access to it.*

### **Exercise: CampusVault Secure Report Viewer**
- **🔍 Problem Before Refactoring**: The reporting system had two major issues:
  1. **Security Violation** — Any `User` could view any `ReportFile`, regardless of classification (PUBLIC, FACULTY, ADMIN).
  2. **Performance Waste** — `ReportFile.display()` triggered an expensive disk load (simulated with `Thread.sleep`) *every single time* it was called, even if the report was already loaded or if the user wasn't authorized to see it.
  3. **Tight Coupling** — `ReportViewer` depended directly on the concrete `ReportFile` class.

- **💡 What is done (Solution)**: An interface was introduced to decouple the viewer, and a **Virtual/Protection Proxy** was implemented to handle security and caching.
- **📝 Modified Existing Files**:
  - `RealReport`: (Formerly `ReportFile`) Extracted the expensive disk-loading logic. It now implements the `Report` interface.
  - `ReportViewer`: Refactored to depend on the `Report` interface instead of a concrete class, adhering to DIP.
  - `App`: Updated to instantiate `ReportProxy` instead of `RealReport`.
- **✨ New Files Created & Their Purpose**:
  - `Report`: The common interface for both the Real Subject and the Proxy.
  - `ReportProxy`: The star of the show. It implements `Report` and holds a lazy reference to `RealReport`. It performs an **Access Control** check *before* loading anything. If authorized, it loads the `RealReport` **exactly once** (Lazy Loading/Caching) and delegates all subsequent calls to it.
  - `AccessControl`: A dedicated logic class to determine if a user role matches a report classification.

> 🎤 **SumUp:**
> *"In this exercise, the `ReportFile` system was both insecure and slow. It didn't check who was opening the report, and it re-loaded the file from disk every single time you hit 'view', causing huge delays. I solved this using the Proxy pattern. I created a `ReportProxy` that stands in front of the `RealReport`. Now, when a user tries to view a report, the proxy first checks their permissions. If they aren't an ADMIN trying to see a 'Budget Audit', it blocks them immediately without ever touching the disk. If they ARE authorized, the proxy loads the real report once and saves it. The second time they view it, it shows the cached version instantly. This adds security and performance without changing the original report loading code."*
