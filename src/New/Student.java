package New;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Student {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private double grade;
    private LocalDate registrationDate;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public Student(int id, String name, String surname,
                   int age, String email, double grade) {

        setId(id);
        setName(name);
        setSurname(surname);
        setAge(age);
        setEmail(email);
        setGrade(grade);
        this.registrationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        try {
            if (id <= 0)
                throw new IllegalArgumentException("ID nomresi musbet reqem olmalidir.");

            this.id = id;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Yalniz reqem daxil edin");
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Ad yeri bosh buraxila bilmez");
        if (!name.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Ad herflerden ibaret olmalidir");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isBlank())
            throw new IllegalArgumentException("Soyad yeri bosh buraxila bilmez");
        if (!surname.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Soyad herflerden ibaret olmalidir");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 16 || age > 60)
            throw new IllegalArgumentException("Univesitet telebesi olmaq uchun yashiniz 16 ve ya yuxari 60 dan ashagi olamalidir");
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException("Email formati duzgun deyil.");
        this.email = email;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Qiymetlendirme 0 ve 100 arasi olmalidir");
        this.grade = grade;
    }

    @Override
    public String toString() {
        return " * Telebenin" + "\n \t Ferdi ID-i: " + id +
                "\n \t Adi: " + name +
                "\n \t Soyadi: " + surname +
                "\n \t Yashi: " + age +
                "\n \t Email: " + email +
                "\n \t Qiymet: " + grade +
                "\n \t Qeydiyyat tarixi: " + registrationDate + "\n\t\t ***\n";
    }
}

