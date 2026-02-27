package New;

import java.util.List;

public interface IStudentService {
    void addStudent(Student student);
    void removeStudent(int id);
    Student findById(int id);
    List<Student> getAllStudents();
    double calculateAverageGrade();
    boolean isEmailTaken(String email);
}
