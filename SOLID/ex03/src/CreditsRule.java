public class CreditsRule implements EligibilityRule {
    private final int minimumCredits;

    public CreditsRule(int minimumCredits) {
        this.minimumCredits = minimumCredits;
    }

    @Override
    public boolean isEligible(StudentProfile s) {
        return s.earnedCredits >= minimumCredits;
    }

    @Override
    public String getReason() {
        return "credits below " + minimumCredits;
    }
}
