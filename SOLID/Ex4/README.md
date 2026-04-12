### So in this exercise, the default code breaks the Open Closed Principle.
### Which basically means that our code should be open for extension but close for modification.

### But in this case, the default code has a lot of switch cases and if/else chains
### for each room and add on type. So, if a new room type or a new service is to be added, one
### has to modify the complete HostelFeeCalculator class. This could risk breaking the code.

### In order to fix this problem, we created two new interfaces, RoomPricing and AddOnPricing which have a getPrice() method
### to get the price of the room or add on.

### For each new room type and add on, we created a new class (total 7).

### Also, instead of hardcoding logic inside the HostelFeeCalculator, we passed a map of interfaces to it.

### Now if a new room type or add on is to be added, we just need to add a new class to the map. Rest of the code stays the same hence following the Open Closed Principle.