import java.util.Scanner;
import java.util.InputMismatchException;

public class TryCatchExample {

    static double[] arr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 0;

        while (true) {
            try {
                System.out.print("Массивын хэмжээг оруулна уу: ");
                size = scanner.nextInt();
                if (size <= 0) {
                    throw new IllegalArgumentException("Хэмжээ 0-ээс их байх ёстой!");
                }
                arr = new double[size];
                break;
            } catch (NegativeArraySizeException e) {
                System.out.println("[Алдаа] Сөрөг хэмжээ оруулсан: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("[Алдаа] " + e.getMessage());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("[Алдаа] Бүхэл тоо оруулна уу!");
                scanner.nextLine();
            } finally {
                System.out.println("[finally] Хэмжээ шалгалт дууслаа.\n");
            }
        }

        System.out.println("Элементүүдээ оруулна уу:");
        for (int i = 0; i < arr.length; i++) {
            while (true) {
                System.out.print("  " + i + "-р элемент: ");
                try {
                    arr[i] = scanner.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("  [Алдаа] Тоо оруулна уу!");
                    scanner.nextLine();
                }
            }
        }

        try {
            System.out.print("\nИндексээ оруулна уу: ");
            int index = scanner.nextInt();
            double value = arr[index];
            double result = 100.0 / value;
            System.out.printf("arr[%d] = %.2f%n", index, value);
            System.out.printf("100 / arr[%d] = %.4f%n", index, result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[Алдаа] Массивын хэмжээнээс хэтэрсэн индекс!");
            System.out.println("Зөв индекс: 0 ~ " + (arr.length - 1));
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("[Алдаа] Бүхэл тоо оруулна уу! (" + e.getClass().getSimpleName() + ")");
        } catch (ArithmeticException e) {
            System.out.println("[Алдаа] Тэгд хуваах боломжгүй!");
        } catch (Exception e) {
            System.out.println("[Алдаа] " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            System.out.println("[finally] Индекс шалгалт дууслаа.");
        }

        System.out.println("\nПрограмм амжилттай дууслаа!");
        scanner.close();
    }
}
