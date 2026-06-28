import java.util.Scanner;

public class lab1b {
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        System.out.print("onoogoo oruulna uu: ");
        int score = key.nextInt();
        
        String grade;

        if (score == 100) {
            grade = "A";
        } else {
            switch (score / 10) {
                case 10:
                case 9:
                    if (score >= 96) grade = "A";
                    else grade = "A-";
                    break;
                case 8:
                    if (score >= 88) grade = "B+";
                    else if (score >= 84) grade = "B";
                    else grade = "B-";
                    break;
                case 7:
                    if (score >= 78) grade = "C+";
                    else if (score >= 74) grade = "C";
                    else grade = "C-";
                    break;
                case 6:
                    if (score >= 68) grade = "D+";
                    else if (score >= 64) grade = "D";
                    else grade = "D-";
                    break;
                default:
                    grade = "F";
                    break;
            }
        }

        System.out.println("dun: " + grade);
    }
}