import java.util.Scanner;
import java.io.*;

public class Main1 {

    static Scanner scanner = new Scanner(System.in);
    static String fileName = "students.dat";

    public static void main(String[] args) {

        System.out.println("=== Student1 програм ===");

        // 1. Гараас мэдээлэл авах
        System.out.print("Ner oruulna uu: ");
        String ner = scanner.nextLine();

        int nas = 0;
        while (true) {
            try {
                System.out.print("Nas oruulna uu: ");
                nas = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Buruu! Too oruulna uu.");
            }
        }

        double gpa = 0;
        while (true) {
            try {
                System.out.print("GPA oruulna uu (0.0-4.0): ");
                gpa = Double.parseDouble(scanner.nextLine());
                if (gpa >= 0.0 && gpa <= 4.0) break;
                System.out.println("GPA 0.0-4.0 baih yostoi!");
            } catch (NumberFormatException e) {
                System.out.println("Buruu! Too oruulna uu.");
            }
        }

        // 2. Объект үүсгэх
        Student1 student = new Student1(ner, nas, gpa);
        System.out.println("\nUusgesan object: " + student);

        // 3. Файлд бичих
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(student);
            System.out.println("Faild hagdalaa: " + fileName);
        } catch (IOException e) {
            System.out.println("Bichihd aldaa: " + e.getMessage());
            return;
        }

        // 4. Файлаас унших
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            Student1 unsan = (Student1) ois.readObject();
            System.out.println("Failaas unshsan: " + unsan);
        } catch (FileNotFoundException e) {
            System.out.println("Fail oldsonguі: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class oldsonguі: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unshihad aldaa: " + e.getMessage());
        }

        System.out.println("\nDuuslaa!");
    }
}
