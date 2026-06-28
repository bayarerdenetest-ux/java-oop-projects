import java.io.*;

public class FileIOExample {

    static final String BYTE_FILE = "byte_data.bin";
    static final String CHAR_FILE = "char_data.txt";
    static final String DATA_FILE = "mixed_data.dat";
    static final String TEXT_FILE = "text_data.txt";

    public static void main(String[] args) {
        writeBytesToFile();
        readBytesFromFile();
        writeCharsToFile();
        readCharsFromFile();
        writeMixedDataToFile();
        readMixedDataFromFile();
        writeTextToFile();
        readTextFromFile();
    }

    static void writeBytesToFile() {
        try (FileOutputStream fos = new FileOutputStream(BYTE_FILE)) {
            byte[] bytes = {65, 66, 67, 68, 69, 10, 77, 78};
            fos.write(bytes);
            fos.write(100);
            System.out.println("Байт бичлээ: " + BYTE_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void readBytesFromFile() {
        try (FileInputStream fis = new FileInputStream(BYTE_FILE)) {
            int byteVal;
            System.out.print("Уншсан байтууд: ");
            while ((byteVal = fis.read()) != -1) {
                if (byteVal != 10)
                    System.out.print((char) byteVal + "(" + byteVal + ") ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("[Алдаа] Файл олдсонгүй: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void writeCharsToFile() {
        try (FileWriter fw = new FileWriter(CHAR_FILE);
             BufferedWriter bw = new BufferedWriter(fw)) {
            char[] chars = {'М', 'о', 'н', 'г', 'о', 'л'};
            bw.write(chars);
            bw.newLine();
            bw.write("Java программчлал");
            bw.newLine();
            bw.write('A');
            System.out.println("Тэмдэгт бичлээ: " + CHAR_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void readCharsFromFile() {
        try (FileReader fr = new FileReader(CHAR_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println("Уншсан: " + line);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void writeMixedDataToFile() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(DATA_FILE))) {
            int[]    ints    = {1, 42, -7, 1000, 99999};
            double[] doubles = {3.14159, -2.718, 0.0, 100.5, 9999.999};
            dos.writeInt(ints.length);
            for (int v : ints)    dos.writeInt(v);
            dos.writeInt(doubles.length);
            for (double v : doubles) dos.writeDouble(v);
            System.out.println("int, double бичлээ: " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void readMixedDataFromFile() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(DATA_FILE))) {
            int intCount = dis.readInt();
            System.out.print("int[]: ");
            for (int i = 0; i < intCount; i++)
                System.out.print(dis.readInt() + " ");
            System.out.println();
            int dblCount = dis.readInt();
            System.out.print("double[]: ");
            for (int i = 0; i < dblCount; i++)
                System.out.printf("%.3f ", dis.readDouble());
            System.out.println();
        } catch (EOFException e) {
            System.out.println("[Анхааруулга] Файлын төгсгөлд хүрлээ.");
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void writeTextToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TEXT_FILE))) {
            pw.println("Сайн уу, Дэлхий!");
            pw.printf("int: %d%n", 42);
            pw.printf("double: %.4f%n", 2.71828);
            pw.printf("boolean: %b%n", true);
            System.out.println("Текст бичлээ: " + TEXT_FILE);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }

    static void readTextFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println("Уншсан: " + line);
        } catch (IOException e) {
            System.out.println("[Алдаа] " + e.getMessage());
        }
    }
}