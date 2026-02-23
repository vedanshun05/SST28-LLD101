import java.util.*;

public class OnboardingService {
    private final StudentRepository db;

    public OnboardingService(StudentRepository db) {
        this.db = db;
    }

    public void registerFromRawInput(String raw) {
        OnboardingPrinter.printInput(raw);

        Map<String, String> kv = InputParser.parse(raw);
        List<String> errors = StudentValidator.validate(kv);

        if (!errors.isEmpty()) {
            OnboardingPrinter.printErrors(errors);
            return;
        }

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        OnboardingPrinter.printSuccess(id, db.count(), rec);
    }
}
