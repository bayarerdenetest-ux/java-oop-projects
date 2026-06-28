import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryListingExample {

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Одоогийн хавтас: " + currentDir + "\n");

        System.out.println("== 1. Бүх файлууд ==");
        listDirectory(currentDir);

        System.out.println("\n== 2. Зөвхөн .java файлууд ==");
        listJavaFiles(currentDir);

        System.out.println("\n== 3. Рекурсив жагсаалт ==");
        listRecursive(new File(currentDir), 0, 3);

        System.out.println("\n== 4. Дэлгэрэнгүй мэдээлэл ==");
        listWithDetails(currentDir);
    }

    static void listDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            System.out.println("[Алдаа] Хавтас олдсонгүй: " + path);
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("[Алдаа] Энэ хавтас биш: " + path);
            return;
        }
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Хавтас хоосон.");
            return;
        }
        int fileCount = 0, dirCount = 0;
        for (File f : files) {
            if (f.getName().startsWith(".")) continue;
            String type = f.isDirectory() ? "[Хавтас]" : "[Файл  ]";
            System.out.printf("  %s %s%n", type, f.getName());
            if (f.isDirectory()) dirCount++;
            else fileCount++;
        }
        System.out.printf("Нийт: %d файл, %d хавтас%n", fileCount, dirCount);
    }

    static void listJavaFiles(String path) {
        File dir = new File(path);
        File[] javaFiles = dir.listFiles((d, name) -> name.endsWith(".java"));
        if (javaFiles == null || javaFiles.length == 0) {
            System.out.println(".java файл олдсонгүй.");
            return;
        }
        for (int i = 0; i < javaFiles.length; i++) {
            System.out.printf("  %2d. %-40s (%,d байт)%n",
                    i + 1, javaFiles[i].getName(), javaFiles[i].length());
        }
    }

    static void listRecursive(File dir, int depth, int maxDepth) {
        if (depth > maxDepth) return;
        String indent = "  ".repeat(depth + 1);
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.getName().startsWith(".")) continue;
            if (f.isDirectory()) {
                System.out.println(indent + "📁 " + f.getName() + "/");
                listRecursive(f, depth + 1, maxDepth);
            } else {
                System.out.println(indent + "📄 " + f.getName());
            }
        }
    }

    static void listWithDetails(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("  %-30s %-8s %-12s %s%n", "Нэр", "Төрөл", "Хэмжээ", "Огноо");
        System.out.println("  " + "-".repeat(70));
        for (File f : files) {
            if (f.getName().startsWith(".")) continue;
            String type = f.isDirectory() ? "Хавтас" : "Файл";
            String size = f.isDirectory() ? "—" : String.format("%,d", f.length());
            String date = sdf.format(new Date(f.lastModified()));
            System.out.printf("  %-30s %-8s %-12s %s%n", f.getName(), type, size, date);
        }
    }
}