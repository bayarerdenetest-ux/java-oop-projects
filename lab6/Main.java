import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        //baraanii angilaluudiin array uusgeh
        ArrayList<ProductCategory> productCategories = new ArrayList<>();

        //baraanii muchirlusun baidlaar hevleh e-commerce

        // --- Электроник ---
        ProductCategory electronics = new ProductCategory("Электроник");

        ProductCategory phones = new ProductCategory("Утас & Таблет");
        ProductCategory cameras = new ProductCategory("Камер & Дуран");
        ProductCategory computers = new ProductCategory("Компьютер & Зөөврийн");

        ProductCategory smartphones = new ProductCategory("Ухаалаг утас");
        ProductCategory tablets = new ProductCategory("Таблет");
        phones.addSubCategory(smartphones);
        phones.addSubCategory(tablets);

        ProductCategory dslrCameras = new ProductCategory("DSLR Камер");
        ProductCategory actionCameras = new ProductCategory("Экшн Камер");
        cameras.addSubCategory(dslrCameras);
        cameras.addSubCategory(actionCameras);

        electronics.addSubCategory(phones);
        electronics.addSubCategory(cameras);
        electronics.addSubCategory(computers);

        // --- Хувцас & Гутал ---
        ProductCategory fashion = new ProductCategory("Хувцас & Гутал");

        ProductCategory mens = new ProductCategory("Эрэгтэй хувцас");
        ProductCategory womens = new ProductCategory("Эмэгтэй хувцас");
        ProductCategory kids = new ProductCategory("Хүүхдийн хувцас");

        ProductCategory mensShirts = new ProductCategory("Цамц & Косовротка");
        ProductCategory mensJackets = new ProductCategory("Куртик & Пальто");
        mens.addSubCategory(mensShirts);
        mens.addSubCategory(mensJackets);

        ProductCategory womensShirts = new ProductCategory("Блуз & Цамц");
        ProductCategory womensDresses = new ProductCategory("Даашинз");
        womens.addSubCategory(womensShirts);
        womens.addSubCategory(womensDresses);

        fashion.addSubCategory(mens);
        fashion.addSubCategory(womens);
        fashion.addSubCategory(kids);

        // --- Авто сэлбэг ---
        ProductCategory auto = new ProductCategory("Авто сэлбэг");

        ProductCategory tires = new ProductCategory("Дугуй & Диск");
        ProductCategory engine = new ProductCategory("Хөдөлгүүрийн сэлбэг");

        ProductCategory winterTires = new ProductCategory("Өвлийн дугуй");
        ProductCategory summerTires = new ProductCategory("Зуны дугуй");
        tires.addSubCategory(winterTires);
        tires.addSubCategory(summerTires);

        auto.addSubCategory(tires);
        auto.addSubCategory(engine);

        // --- Гэр орон ---
        ProductCategory home = new ProductCategory("Гэр & Амьдрал");
        ProductCategory furniture = new ProductCategory("Тавилга");
        ProductCategory kitchen = new ProductCategory("Гал тогооны хэрэгсэл");
        home.addSubCategory(furniture);
        home.addSubCategory(kitchen);

        // Үндсэн ангилалуудыг ArrayList-д нэмэх
        productCategories.add(electronics);
        productCategories.add(fashion);
        productCategories.add(auto);
        productCategories.add(home);

      
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      БАРААНЫ АНГИЛАЛЫН МӨЧИРЛӨСӨН БҮТЭЦ          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        for (ProductCategory cat : productCategories) {
            cat.printTree(0);
        }

        // ============================================================
        // 4. Бараануудыг үүсгэж ангилалдаа нэмэх
        // ============================================================

        // -- Ухаалаг утасны бараанууд (Smartphone) --
        smartphones.addProduct(new SmartPhone (
                "iPhone 15 Pro Max", 4_500_000, "Apple", "15 Pro Max", 256, "Хар"));
        smartphones.addProduct(new SmartPhone(
                "Samsung Galaxy S24 Ultra", 3_900_000, "Samsung", "S24 Ultra", 512, "Цагаан"));
        smartphones.addProduct(new SmartPhone(
                "Xiaomi 14 Pro", 2_800_000, "Xiaomi", "14 Pro", 128, "Саарал"));

        // -- DSLR Камер --
        dslrCameras.addProduct(new DigitalCamera(
                "Canon EOS R6 Mark II", 5_200_000, DigitalCamera.CameraType.DSLR, "Canon"));
        dslrCameras.addProduct(new DigitalCamera(
                "Nikon Z8 Mirrorless", 6_800_000, DigitalCamera.CameraType.DSLR, "Nikon"));
        dslrCameras.addProduct(new DigitalCamera(
                "Sony ZV-1F Vlog Camera", 1_800_000, DigitalCamera.CameraType.VIDEO, "Sony"));

        // -- Экшн Камер --
        actionCameras.addProduct(new DigitalCamera(
                "GoPro Hero 12 Black", 1_650_000, DigitalCamera.CameraType.VIDEO, "GoPro"));
        actionCameras.addProduct(new DigitalCamera(
                "DJI Osmo Pocket 3", 1_900_000, DigitalCamera.CameraType.DRONE, "DJI"));

        // -- Эрэгтэй цамц --
        mensShirts.addProduct(new Clothing(
                "Оксфорд цагаан цамц", 89_000, "L", "Цагаан", Clothing.Gender.MEN));
        mensShirts.addProduct(new Clothing(
                "Polo цамц Nike", 120_000, "XL", "Хөх", Clothing.Gender.MEN));

        // -- Эрэгтэй куртик --
        mensJackets.addProduct(new Clothing(
                "The North Face куртик", 450_000, "L", "Хар", Clothing.Gender.MEN));
        mensJackets.addProduct(new Clothing(
                "Adidas Tracksuit", 280_000, "M", "Цэнхэр", Clothing.Gender.MEN));

        // -- Эмэгтэй хувцас --
        womensShirts.addProduct(new Clothing(
                "Зефир блуз", 75_000, "S", "Ягаан", Clothing.Gender.WOMEN));
        womensDresses.addProduct(new Clothing(
                "Хар оройн даашинз", 320_000, "M", "Хар", Clothing.Gender.WOMEN));

        // -- Хүүхдийн хувцас --
        kids.addProduct(new Clothing(
                "Хүүхдийн трактор принт цамц", 45_000, "6-7 нас", "Улаан", Clothing.Gender.KIDS));
        kids.addProduct(new Clothing(
                "Хүүхдийн пижама", 62_000, "8-9 нас", "Ногоон", Clothing.Gender.KIDS));

        // -- Өвлийн дугуй --
        winterTires.addProduct(new Tire(
                "Michelin X-Ice North 4", 380_000, Tire.Season.WINTER, 205, 55, 16));
        winterTires.addProduct(new Tire(
                "Nokian Hakkapeliitta 10", 420_000, Tire.Season.WINTER, 265, 55, 18));
        winterTires.addProduct(new Tire(
                "Bridgestone Blizzak WS90", 350_000, Tire.Season.WINTER, 185, 65, 15));

        // -- Зуны дугуй --
        summerTires.addProduct(new Tire(
                "Michelin Pilot Sport 5", 290_000, Tire.Season.SUMMER, 225, 45, 17));
        summerTires.addProduct(new Tire(
                "Continental SportContact 7", 310_000, Tire.Season.SUMMER, 255, 40, 19));

        // -- Гэр орон --
        furniture.addProduct(new Product("Дуб материалтай ширээ", 850_000));
        furniture.addProduct(new Product("Эргэдэг компьютерийн сандал", 420_000));
        kitchen.addProduct(new Product("Tefal хоолны сав иж бүрдэл", 185_000));
        kitchen.addProduct(new Product("Philips аэрофрайер", 380_000));

        // -- Компьютер --
        computers.addProduct(new Product("MacBook Pro 14\" M3", 5_800_000));
        computers.addProduct(new Product("Dell XPS 15 Зөөврийн", 4_200_000));

        // ============================================================
        // 5. Барааны ангилалын дагуу мөчирлөсөн байдлаар хэвлэх
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║          БАРААНЫ АНГИЛАЛ ДҮН ТУС БҮРИЙН БАРААНУУД                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
        for (ProductCategory cat : productCategories) {
            cat.printProductTree(0);
            System.out.println();
        }

        // ============================================================
        // 6. Нийт бараа тоог гаргах
        // ============================================================
        System.out.println("════════════════════════════════════════════════════════════════════════════");
        System.out.println("Нийт үндсэн ангилалын тоо: " + productCategories.size());
    }
}