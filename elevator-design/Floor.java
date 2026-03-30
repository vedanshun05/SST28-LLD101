public class Floor {
    private final int floorNumber;
    private final FloorPanel upPanel;
    private final FloorPanel downPanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upPanel = new FloorPanel(floorNumber, true);
        this.downPanel = new FloorPanel(floorNumber, false);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public FloorPanel getUpPanel() {
        return upPanel;
    }

    public FloorPanel getDownPanel() {
        return downPanel;
    }

    public static class FloorPanel {
        private final int floorNumber;
        private final boolean isUpButton;
        private boolean isPressed;

        public FloorPanel(int floorNumber, boolean isUpButton) {
            this.floorNumber = floorNumber;
            this.isUpButton = isUpButton;
            this.isPressed = false;
        }

        public void press() {
            if (!isPressed) {
                isPressed = true;
                System.out.println(String.format("Floor %d: %s button pressed", 
                    floorNumber, isUpButton ? "UP" : "DOWN"));
            }
        }

        public void reset() {
            isPressed = false;
        }

        public boolean isPressed() {
            return isPressed;
        }

        public int getFloorNumber() {
            return floorNumber;
        }

        public boolean isUpButton() {
            return isUpButton;
        }
    }
}
