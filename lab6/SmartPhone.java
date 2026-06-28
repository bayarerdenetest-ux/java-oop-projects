public class SmartPhone extends Product{
    private String brand;
    private String model;
    private int storageGB;
    private String color;
    
    public SmartPhone( String name, double price, String brand, String model, int storageGB, String color){
        super(name, price);
        this.brand = brand;
        this.model = model;
        this.storageGB = storageGB;
        this.color = color;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel (){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public int getStorageGB(){
        return storageGB;
    }
    public void setStorageGB(int storageGB){
        this.storageGB = storageGB;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    
    @Override
    public String getProductInfo(){
        return String.format(" Phone %-38s / une= %,.0f$ / %s %s / %dGB / %s", getName(), getPrice(), brand, model,storageGB, color);
    }
}
