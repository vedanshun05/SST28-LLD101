# 🪶 SST28-LLD101: Flyweight Pattern — Map Marker Style Exercise

Welcome to the Flyweight Pattern exercise! This project demonstrates identifying and fixing a memory-inefficient system that creates thousands of duplicate objects by extracting shared **intrinsic state**.

---

## 🏗️ Flyweight Design Pattern
*Use sharing to support large numbers of fine-grained objects efficiently.*

### **Exercise: Map Markers Style Deduplication**
- **🔍 Problem Before Refactoring**: A map rendering tool (GeoDash) created thousands of `MapMarker` objects. Each marker held its own `MarkerStyle` (shape, color, size, filled flag). Since there were only a few unique styles but thousands of markers, the system was wasting massive amounts of memory on duplicate style objects.
- **💡 What is done (Solution)**: The marker's style was extracted into an immutable Flyweight, and a factory was introduced to cache and share these styles.
- **📝 Modified Existing Files**:
  - `MarkerStyle`: Refactored to be **immutable** (all fields `final`, no setters). Added `equals()` and `hashCode()` for correct factory caching.
  - `MapMarker`: Modified to store only **extrinsic state** (lat, lng, label) and a reference to a shared `MarkerStyle` instead of creating its own.
  - `MapDataSource`: Updated to use `MarkerStyleFactory` to obtain style instances instead of calling `new MarkerStyle(...)`.
  - `App`: Updated to show the count of unique style instances in the factory.
- **✨ New Files Created & Their Purpose**:
  - `MarkerStyleFactory`: Created to manage the cache of `MarkerStyle` objects. It uses a composite string key (`shape|color|size|filled`) to return existing instances or create new ones only when necessary.

> 🎤 **SumUp:**
> *"In this exercise, the map system was bleeding memory because every single marker on the map was creating its own private `MarkerStyle` object. Even if 10,000 markers were all 'Red Circles', we had 10,000 separate style objects in memory. I fixed this using the Flyweight pattern. First, I made `MarkerStyle` completely immutable. Then, I introduced a `MarkerStyleFactory` that acts as a cache. Now, when `MapDataSource` creates a marker, it asks the factory for a style. If 'Red Circle' already exists in the cache, it returns that same instance. Now, 10,000 markers share just a few dozen style objects, dramatically reducing the memory footprint while keeping the rendering output exactly the same."*
