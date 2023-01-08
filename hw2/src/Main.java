import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter working directory (blank to current)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String getWorkingDirectory = reader.readLine();
        if (getWorkingDirectory == null) {
            System.out.println("Work with current directory");
            getWorkingDirectory = System.getProperty("user.dir");
        }
        File f = new File(getWorkingDirectory);
        if (!f.exists()) {
            System.out.println("Directory not found. Work with current");
            getWorkingDirectory = System.getProperty("user.dir");
        }
        GraphBuilder graph = new GraphBuilder(getWorkingDirectory);
        graph.fillFileGraph();
        graph.sortFileGraph();
        var sorted = graph.getSortedFileGraph();
        for (var file : sorted) {
            System.out.println(file.toString());
        }
        graph.concatenateAllFiles();
    }
}