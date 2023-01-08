import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// Класс посторение графа файлов для проверки на цикличность
public class GraphBuilder {
    private final HashMap<Path, ArrayList<Path>> fileGraph;
    private final HashMap<Path, Integer> color;
    private final ArrayList<Path> sortedFileGraph;
    private final String workingDirectory;

    // Конструктор
    // String directory - рабочая директория класса
    public GraphBuilder(String directory) {
        fileGraph = new HashMap<>();
        sortedFileGraph = new ArrayList<>();
        color = new HashMap<>();
        workingDirectory = directory;
    }

    // Функция для получения списка файлов в рабочей папке.
    private ArrayList<Path> getListOfFile(Path directoryPath) throws IOException {
        Stack<Path> stack = new Stack<>();
        ArrayList<Path> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(directoryPath.toFile().listFiles())) {
            Path path = Paths.get(file.toString());
            if (file.isDirectory()) {
                stack.push(path);
            } else {
                files.add(path);
            }
        }
        while (!stack.isEmpty()) {
            DirectoryStream<Path> stream = Files.newDirectoryStream(stack.pop());
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    stack.push(entry);
                } else {
                    files.add(entry);
                }
            }
            stream.close();
        }
        return files;
    }

    // Функция заполнения графа зависимостей файлов.
    public void fillFileGraph() throws IOException {
        var listOfFile = getListOfFile(Paths.get(workingDirectory));
        for (var file : listOfFile) {
            fileGraph.put(file, FileParser.getFileDependence(file.toString(), workingDirectory));
            color.put(file, 0);
        }
    }

    private void dfs(Path currentVertex) {
        if (color.get(currentVertex) == 1) {
            System.out.println("Files cycled, can't sort and concatenate list");
            System.out.println("Broken file is: " + currentVertex.toString());
            System.exit(0);
        }
        if (color.get(currentVertex) == 2) {
            return;
        }
        color.replace(currentVertex, 1);
        for (int i = 0; i < fileGraph.get(currentVertex).size(); ++i) {
            dfs(fileGraph.get(currentVertex).get(i));
        }
        sortedFileGraph.add(currentVertex);
        color.replace(currentVertex, 2);
    }

    // Функция сортировки графа и проверки его на ацикличность
    public void sortFileGraph() {
        sortedFileGraph.clear();
        for (var file : fileGraph.keySet()) {
            if (color.get(file) == 0)
                dfs(file);
        }
    }

    // геттер отсортированного графа зависимостей файлов.
    public ArrayList<Path> getSortedFileGraph() {
        return sortedFileGraph;
    }

    // Функция для конкатенации всего содержимого файлов в 1.
    public void concatenateAllFiles() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Answer.txt"));
        for (var file : sortedFileGraph) {
            var buffer = Files.readAllLines(file);
            for (var line : buffer)
                writer.write(line + '\n');
        }
        writer.close();
    }
}
