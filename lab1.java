import java.util.Scanner;
public class lab1{
    public static void main(String[] args){
        Scanner key = new Scanner(System.in);
        System.out.println("onoogoo oruulna uu");
        int score = key.nextInt();
        
        String grade;
        
        if(score >= 96 && score <= 100){
            grade = "A";
        } else if (score >=91){
            grade = "A-";
        }
        else if (score >= 88){
            grade = "B+";
        }
        else if (score >=84){
            grade = "B";
        }
        else if (score >= 81){
            grade = "B-";
        }
        else if (score >= 78){
            grade = "C+";
        }
        else if (score >= 74){
            grade = "C";
        }
        else if (score >= 71){
            grade = "C-";
        }
         else if (score >= 68) {
            grade = "D+";
        } else if (score >= 64) {
            grade = "D";
        } else if (score >= 60) {
            grade = "D-";
        } else {
            grade = "F";
        }
        System.out.println(" ur dun" + grade);
    }
}