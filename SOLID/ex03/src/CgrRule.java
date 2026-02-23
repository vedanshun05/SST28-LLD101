public class CgrRule implements EligibilityRule {
    private final double minimumCgr;

    public CgrRule(double minimumCgr) {
        this.minimumCgr = minimumCgr;
    }

    @Override
    public boolean isEligible(StudentProfile s) {
        return s.cgr >= minimumCgr;
    }

    @Override
    public String getReason() {
        return "CGR below " + minimumCgr;
    }
}
