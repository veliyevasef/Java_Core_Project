package New;

import java.util.*;

public class StudentServiceImpl implements IStudentService {
    private final Map<Integer, Student> students = new HashMap<>();
    private final Set<String> emails = new HashSet<>();

    @Override
    public void addStudent(Student student) {

        if (students.containsKey(student.getId()))
            throw new RuntimeException("ID nomresi artiq movcuddur");

        if (emails.contains(student.getEmail()))
            throw new RuntimeException("Email artiq movcuddur");

        students.put(student.getId(), student);
        emails.add(student.getEmail());
    }

    @Override
    public void removeStudent(int id) {

        Student removed = students.remove(id);

        if (removed == null)
            throw new RuntimeException("Telebe movcud deyil");

        emails.remove(removed.getEmail());
    }

    @Override
    public Student findById(int id) {

        Student student = students.get(id);

        if (student == null)
            throw new RuntimeException("Telebe movcud deyil");

        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public double calculateAverageGrade() {

        return students.values()
                .stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0);
    }

    @Override
    public boolean isEmailTaken(String email) {

        return students.values().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
    }
}


