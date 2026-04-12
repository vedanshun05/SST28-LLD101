public class AttendanceScanner implements PowerControllable, AttendanceScannable {
    @Override public void powerOn() {  }
    @Override public void powerOff() {  }

    @Override public int scanAttendance() { return 3; }
}
