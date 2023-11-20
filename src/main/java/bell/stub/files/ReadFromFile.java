package bell.stub.files;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ReadFromFile {

    private static String path = "src/main/java/bell/stub/files/storage/";
    private static String fileName = "text.txt";

    public static String readFromFile() {
        File file = new File(path + fileName);

        String randomLine = null;

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileSize = randomAccessFile.length();

            Random random = new Random();
            long randomPosition = random.nextLong(randomAccessFile.length()) % fileSize;
            randomPosition = Math.abs(randomPosition);

            randomAccessFile.seek(randomPosition);
            randomAccessFile.readLine();

            randomLine = new String(randomAccessFile.readLine().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomLine;
    }
}
