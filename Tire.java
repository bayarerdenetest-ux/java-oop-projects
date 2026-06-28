public class Tire extends Product {

    public enum Season {
        WINTER("Өвлийн"),
        SUMMER("Зуны"),
        ALL_SEASON("Бүх улирлын");

        private final String label;
        Season(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    private Season season;   // Ямар улирлынх
    private int size1;       // Хэмжээс1 (жишээ нь: 265)
    private int size2;       // Хэмжээс2 (жишээ нь: 55)
    private int size3;       // Хэмжээс3 (жишээ нь: 18)

    public Tire(String name, double price, Season season, int size1, int size2, int size3) {
        super(name, price);
        this.season = season;
        this.size1 = size1;
        this.size2 = size2;
        this.size3 = size3;
    }

    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }
    public int getSize1() { return size1; }
    public int getSize2() { return size2; }
    public int getSize3() { return size3; }
    public String getSizeString() { return size1 + "/" + size2 + "/" + size3; }

    @Override
    public String getProductInfo() {
        return String.format("🔧 %-38s | Үнэ: %,.0f₮ | %s | Хэмжээ: %s",
                getName(), getPrice(), season.getLabel(), getSizeString());
    }
}