import java.util.Scanner;
public class MinElement{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("massiviin hemjeeg oruulna uu: ");
        int size = scanner.nextInt();
        double[] arr = new double[size];
        System.out.println("elementuudig oruulna uu: ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(i + "-j element: ");
            arr[i] =scanner.nextDouble();
        }
        double min = arr[0];
        for(int i = 1; i< arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        int a = (int) min;
        System.out.println("hamgiin baga element(double): "+min);
        System.out.println("buhel toon turliin a huvsagch: " +a);
    }
}
