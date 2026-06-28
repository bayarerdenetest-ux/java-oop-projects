
public class DigitalCamera extends Product {

    public enum CameraType {
        POINT_AND_SHOOT("Point & Shoot"),
        DSLR("DSLR"),
        VIDEO("Видео"),
        DRONE("Drone"),
        WEB("Вэб камер");

        private final String label;
        CameraType(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    private CameraType type;   // Камерын төрөл
    private String brand;      // Брэнд

    public DigitalCamera(String name, double price, CameraType type, String brand) {
        super(name, price);
        this.type = type;
        this.brand = brand;
    }

    public CameraType getType() { return type; }
    public void setType(CameraType type) { this.type = type; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    @Override
    public String getProductInfo() {
        return String.format("📷 %-38s | Үнэ: %,.0f₮ | %s | %s",
                getName(), getPrice(), brand, type.getLabel());
    }
}