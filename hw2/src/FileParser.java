import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// Класс для нахождения всех зависимостей файла.
public class FileParser {
    // Проверка файла на существование.
    private static boolean checkFileExist (String filePathString) {
        File f = new File(filePathString);
        return f.exists() && !f.isDirectory();
    }

    // Составление списка зависимостей
    public static ArrayList<Path> getFileDependence(String filePathString, String basicDirectory) throws IOException {
        ArrayList<Path> fileDependence = new ArrayList<>();
        if (checkFileExist(filePathString)) {
            File file = new File(filePathString);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String stringBuffer;
            while ((stringBuffer = buffer.readLine()) != null) {
                if (stringBuffer.startsWith("require")) {
                    var splitedString = stringBuffer.split("'");
                    if (splitedString.length != 2) {
                        System.out.println("String: " + stringBuffer + " is incorrect");
                    } else {
                        if (checkFileExist(basicDirectory + '/' + splitedString[1]))
                            fileDependence.add(Paths.get(basicDirectory + '/' + splitedString[1]));
                    }
                }
            }
        } else {
            System.out.println("File: " + filePathString + " isn't exist");
        }
        return fileDependence;
    }
}
