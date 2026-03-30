public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Powerable pjPower = reg.getFirst(Powerable.class);
        pjPower.powerOn();
        InputConnectable pjInput = reg.getFirst(InputConnectable.class);
        pjInput.connectInput("HDMI-1");

        BrightnessControllable lights = reg.getFirst(BrightnessControllable.class);
        lights.setBrightness(60);

        TemperatureControllable ac = reg.getFirst(TemperatureControllable.class);
        ac.setTemperatureC(24);

        Scannable scanner = reg.getFirst(Scannable.class);
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        // Shut down all Powerable devices that produce output
        // We need specific devices for shutdown in the right order
        for (Object d : new Object[] {
                reg.getFirst(Projector.class),
                reg.getFirst(LightsPanel.class),
                reg.getFirst(AirConditioner.class)
        }) {
            ((Powerable) d).powerOff();
        }
    }
}
