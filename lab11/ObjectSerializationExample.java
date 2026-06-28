import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int    age;
    private double gpa;
    private String major;

    public Student(String name, int age, double gpa, String major) {
        this.name  = name;
        this.age   = age;
        this.gpa   = gpa;
        this.major = major;
    }

    public String getName()  { return name;  }
    public int    getAge()   { return age;   }
    public double getGpa()   { return gpa;   }
    public String getMajor() { return major; }

    @Override
    public String toString() {
        return String.format("Student{нэр='%s', нас=%d, GPA=%.2f, мэргэжил='%s'}",
                name, age, gpa, major);
    }
}

public class ObjectSerializationExample {

    static final String SINGLE_FILE = "student.ser";
    static final String LIST_FILE   = "students_list.ser";

    public static void main(String[] args) {
        singleObjectDemo();
        listOfObjectsDemo();
    }

    static void singleObjectDemo() {
        Student s = new Student("Болд Дорж", 21, 3.85, "Компьютер Шинжлэх Ухаан");
        System.out.println("Бичиж буй объект: " + s);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SINGLE_FILE))) {
            oos.writeObject(s);
            System.out.println("Файлд бичлээ: " + SINGLE_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SINGLE_FILE))) {
            Student loaded = (Student) ois.readObject();
            System.out.println("Уншсан объект: " + loaded);
            System.out.println("Нэр: "     + loaded.getName());
            System.out.println("Нас: "     + loaded.getAge());
            System.out.printf( "GPA: %.2f%n", loaded.getGpa());
            System.out.println("Мэргэжил: " + loaded.getMajor());
        } catch (FileNotFoundException e) {
            System.out.println("[Алдаа] Файл олдсонгүй: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Алдаа] Класс олдсонгүй: " + e.getMessage());
        } catch (InvalidClassException e) {
            System.out.println("[Алдаа] serialVersionUID таарсангүй: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void listOfObjectsDemo() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Сарнай Баяр",  20, 3.92, "Математик"));
        students.add(new Student("Тэмүүлэн Гал", 22, 3.41, "Физик"));
        students.add(new Student("Оюунтөгс Ням", 19, 3.75, "Химийн Инженер"));

        for (Student st : students)
            System.out.println("+ " + st);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LIST_FILE))) {
            oos.writeObject(students);
            System.out.println("Жагсаалт бичлээ: " + LIST_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LIST_FILE))) {
            @SuppressWarnings("unchecked")
            List<Student> loaded = (List<Student>) ois.readObject();
            System.out.println("Нийт " + loaded.size() + " оюутан уншлаа:");
            for (int i = 0; i < loaded.size(); i++) {
                Student st = loaded.get(i);
                System.out.printf("%d. %-20s | нас: %2d | GPA: %.2f | %s%n",
                        i + 1, st.getName(), st.getAge(), st.getGpa(), st.getMajor());
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("[Алдаа] " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
