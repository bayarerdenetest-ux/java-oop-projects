import java.util.Scanner;
public class FibonacciRecursivee {
    public static int fibonacci(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){ 
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void printFib(int i, int n){
        if(i == n) return;

        System.out.print(fibonacci(i) + " ");
        printFib(i + 1, n);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("hed dahi fibonacci too oloh ve: ");
        int n = sc.nextInt();

        printFib(0, n);
    }
}
