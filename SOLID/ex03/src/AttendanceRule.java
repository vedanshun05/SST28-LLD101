public class AttendanceRule implements EligibilityRule {
    private final int minimumAttendance;

    public AttendanceRule(int minimumAttendance) {
        this.minimumAttendance = minimumAttendance;
    }

    @Override
    public boolean isEligible(StudentProfile s) {
        return s.attendancePct >= minimumAttendance;
    }

    @Override
    public String getReason() {
        return "attendance below " + minimumAttendance;
    }
}
