abstract class UniversityMember implements User{
    private String UserName;
    public abstract boolean Login(String UserName, String Password);
    public abstract void Logout();
    public void showNews(){
        System.out.println("university news");
    }
    public void showAdvertising(){
        System.out.println(" university ad");
    }
    
}
