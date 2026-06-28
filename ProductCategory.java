import java.util.ArrayList;

public class ProductCategory{
    private String name; //name
    private ProductCategory parentCategory; //etsgiing angilal
    private ArrayList<ProductCategory> subCategories; //arraylist
    private ArrayList<Product> products;
    
    public ProductCategory(String name){
        this.name = name;
        this.parentCategory = null; //null ashiglasan etseg baihgui
        this.subCategories = new ArrayList<>(); //array zarlasan
        this.products = new ArrayList<>();
    }
    public ProductCategory(String name, ProductCategory parentCategory){
        this.name = name;
        this.parentCategory = parentCategory; //etseg damjuulna
        this.subCategories = new ArrayList<>();
        this.products = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void setName(String name){this.name = name;}
    public ProductCategory getCategory(){ return parentCategory;}
    public void setParentCategore(ProductCategory parentCategory){this.parentCategory = parentCategory;}
    public ArrayList<ProductCategory> getSubCategories(){return subCategories;}
    public ArrayList<Product> getProducts(){return products;
    }
    public void addSubCategory(ProductCategory subCategory) { //add subCategory
        subCategory.setParentCategore( this );
        this.subCategories.add(subCategory);
    }
    public void addProduct(Product product){ //add Product
        product.setCategory(this);
        this.products.add(product);
    }
    public void printTree(int indent){
        String prefix = " ".repeat(indent);
        String market = indent == 0? "hairtsag" : "/-";
        System.out.println(prefix + market + name);
        for(ProductCategory sub : subCategories){
            sub.printTree(indent + 1);
        }
    }
    public void printProductTree(int indent) {
        String prefix = "  ".repeat(indent);
        String catMarker = indent == 0 ? "📂 " : "📁 ";
        System.out.println(prefix + catMarker + "[" + name + "]");
 
        // Энэ ангилалын бараануудыг хэвлэх
        for (Product p : products) {
            System.out.println(prefix + "  🛒 " + p.getProductInfo());
        }
 
        // Дэд ангилалуудын барааг хэвлэх
        for (ProductCategory sub : subCategories) {
            sub.printProductTree(indent + 1);
        }
    }
    @Override
    public String toString() {
        return "ProductCategory{name='" + name + "'}";
    }
}