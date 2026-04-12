import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repository, InputParser parser, StudentValidator validator, OnboardingPrinter printer) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord record = new StudentRecord(
                id,
                data.get("name"),
                data.get("email"),
                data.get("phone"),
                data.get("program"));

        repository.save(record);
        printer.printSuccess(record, repository.count());
    }
}
