import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Мөрийн тоог гараас авах
        System.out.print("Мөрийн тоо оруулна уу: ");
        int rows = sc.nextInt();

        // Jagged массив үүсгэх
        int[][] a = new int[rows][];

        // Мөр бүрийн баганын тоо болон утгыг гараас авах
        for (int i = 0; i < rows; i++) {
            System.out.print(i + "-р мөрийн баганын тоо: ");
            int cols = sc.nextInt();
            a[i] = new int[cols];

            for (int j = 0; j < cols; j++) {
                System.out.print("  a[" + i + "][" + j + "] = ");
                a[i][j] = sc.nextInt();
            }
        }

        // Хэвлэх
        System.out.println("\nМассивын элементүүд:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print("a[" + i + "][" + j + "]=" + a[i][j] + "  ");
            }
            System.out.println();
        }

        // Сондгой баганын нийлбэр
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (j % 2 != 0) {
                    sum += a[i][j];
                }
            }
        }

        System.out.println("\nСондгой баганын нийлбэр: " + sum);
    }
}
