import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        List<EligibilityRule> rules = List.of(
                new DisciplinaryFlagRule(),
                new CgrRule(8.0),
                new AttendanceRule(75),
                new CreditsRule(20));

        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules);
        engine.runAndPrint(s);

        // Validation: eligible student
        System.out.println();
        System.out.println("=== Validation ===");
        StudentProfile s2 = new StudentProfile("23BCS1002", "Priya", 8.5, 80, 22, LegacyFlags.NONE);
        engine.runAndPrint(s2);
    }
}
