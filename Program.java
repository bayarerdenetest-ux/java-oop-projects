public class Program{
    public static void main(String[] args){
        Student s = new Student();
        s.Login("Student1", "123");
        s.showNews();
        s.showAdvertising();
        s.Logout();
        
        System.out.println("---------------");
        Employee e = new Employee();
        e.Login("emp1", "456");
        e.showNews();
        e.showAdvertising();
        e.Logout();
        
        System.out.println("----------------");
        Teacher t = new Teacher();
        t.Login("teacher1", "256");
        t.showNews();
        t.showAdvertising();
        t.Logout();
        System.out.println("-----------------");
        
        Guest g = new Guest();
        g.showNews();
        g.showAdvertising();
    }
    
}