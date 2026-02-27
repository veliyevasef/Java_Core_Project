package New;

import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IStudentService service = new StudentServiceImpl();

        while (true) {

            System.out.println("\n====== Student Management System =======");
            System.out.println("1. Yeni tələbə əlavə et");
            System.out.println("2. Tələbə siyahısını göstər");
            System.out.println("3. ID ilə tələbə axtar");
            System.out.println("4. Tələbəni sil");
            System.out.println("5. Tələbə məlumatlarını yenilə");
            System.out.println("6. Orta balı hesabla");
            System.out.println("7. Çıxış");
            System.out.print("\nSeçiminizi reqemle daxil edin: ");

            int choice = sc.nextInt();


            try {

                switch (choice) {

                    case 1:
                        String id ;
                        while (true) {
                            System.out.print("Ferdi nomresi (ID): ");
                            id = sc.nextLine();
                            sc.nextLine();

                            try {
                                if (service.findById(Integer.parseInt(id)) != null) {
                                    System.out.println("Bu ID artiq movcuddur. Bashqa ID daxil edin.");
                                    continue;
                                }
                            } catch (RuntimeException e) {
                                // tapılmadısa problem yoxdur
                            }

                            try {
                                Student temp = new Student(id, "Temp", "Temp", 20, "test@mail.com", 0);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage());
                            }
                        }

                        String name;
                        while (true) {
                            System.out.print("Adi: ");
                            name = sc.nextLine();
                            try {

                                Student temp = new Student(id, name, "Temp", 20, "test@mail.com", 0);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage() + ". Yenidən daxil edin.");
                            }
                        }

                        String surname;
                        while (true) {
                            System.out.print("Soyadi: ");
                            surname = sc.nextLine();
                            try {

                                Student temp = new Student(id, name, surname, 20, "test@mail.com", 0);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage() + ". Yenidən daxil edin.");
                            }
                        }


                        int age;
                        while (true) {
                            System.out.print("Yashi (min 16,max 60) : ");
                            age = sc.nextInt();
                            sc.nextLine();
                            try {

                                Student temp = new Student(id, "Temp", "Temp", age, "test@mail.com", 0);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage());
                            }
                        }


                        String email;

                        while (true) {
                            System.out.print("Email: ");
                            email = sc.nextLine().trim();

                            try {
                                // format yoxlama
                                if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
                                    throw new IllegalArgumentException("Email formati sehvdir");

                                // duplicate yoxlama
                                if (service.isEmailTaken(email)) {
                                    System.out.println("Bu email artiq qeydiyyatdadir.");
                                    continue;
                                }

                                break; // email düzgündür və unikaldır

                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage());
                            }
                        }

                        double grade;
                        while (true) {
                            System.out.print("Qiymet: ");
                            grade = sc.nextDouble();
                            try {

                                Student temp = new Student(id, "Temp", "Temp", 20, "test@mail.com", grade);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Xeta: " + e.getMessage());
                            }
                        }

                        Student student = new Student(id, name, surname, age, email, grade);

                        try {
                            service.addStudent(student);
                            System.out.println("Telebe ugurla elave edildi.");
                        } catch (Exception e) {
                            System.out.println("Xeta: " + e.getMessage());
                        }
                        break;

                    case 2:
                        service.getAllStudents()
                                .forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("ID nomresini daxil et: ");
                        int searchId = sc.nextInt();
                        System.out.println(service.findById(searchId));
                        break;

                    case 4:
                        System.out.print("ID nomresini daxil et: ");
                        int deleteId = sc.nextInt();
                        service.removeStudent(deleteId);
                        System.out.println("Telebe ugurla silindi.");
                        break;

                    case 5:
                        System.out.print("ID nomresini daxil et: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        Student st = service.findById(updateId);

                        System.out.print("Yeni Email: ");
                        String newEmail = sc.nextLine();

                        System.out.print("Telebeni qiymetlendir: ");
                        double newGrade = sc.nextDouble();

                        st.setEmail(newEmail);
                        st.setGrade(newGrade);

                        System.out.println("Ugurla yenilendi.");
                        break;

                    case 6:
                        System.out.println("Ortalama qiymet: "
                                + service.calculateAverageGrade());
                        break;

                    case 7:
                        System.out.println("Chixish edilir...");
                        return;

                    default:
                        System.out.println("Qusurlu sechim.");

                }

            } catch (Exception e) {
                System.out.println("Xeta: " + e.getMessage());
            }
        }
    }
}
