import java.util.List;

public interface StudentRepository {
    void save(StudentRecord student);
    int count();
    List<StudentRecord> all();
}
