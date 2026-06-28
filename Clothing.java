
public class Clothing extends Product {

    public enum Gender {
        MEN("Эрэгтэй"),
        WOMEN("Эмэгтэй"),
        UNISEX("Унисекс"),
        KIDS("Хүүхэд");

        private final String label;
        Gender(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    private String size;     // Хэмжээ: S, M, L, XL, XXL
    private String color;    // Өнгө
    private Gender gender;   // Хэний

    public Clothing(String name, double price, String size, String color, Gender gender) {
        super(name, price);
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    @Override
    public String getProductInfo() {
        return String.format("👕 %-38s | Үнэ: %,.0f₮ | %s | %s | %s",
                getName(), getPrice(), gender.getLabel(), size, color);
    }
}