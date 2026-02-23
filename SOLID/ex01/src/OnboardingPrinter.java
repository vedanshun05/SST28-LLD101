import java.util.List;

public class OnboardingPrinter {
    public static void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public static void printErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors)
            System.out.println("- " + e);
    }

    public static void printSuccess(String id, int count, StudentRecord rec) {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
