import java.io.Serializable;

public class Student1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ner;
    private int nas;
    private double gpa;

    public Student1(String ner, int nas, double gpa) {
        this.ner = ner;
        this.nas = nas;
        this.gpa = gpa;
    }

    public String getNer()  { return ner; }
    public int getNas()     { return nas; }
    public double getGpa()  { return gpa; }

    public String toString() {
        return "Ner: " + ner + ", Nas: " + nas + ", GPA: " + gpa;
    }
}
