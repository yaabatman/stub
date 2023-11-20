package bell.stub.files;

import bell.stub.models.User;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private static String fileName = "src/main/java/bell/stub/files/storage/output.csv";

    public static void writeToChosenFile(User user) {

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(user.toCSVstring());
            fileWriter.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
