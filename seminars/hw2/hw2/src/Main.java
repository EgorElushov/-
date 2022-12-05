import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    private static String[] students = new String[] {
            "Амирханов Никита Русланович",
            "Артемов Никита Владиславович",
            "Барков Борис Геннадьевич",
            "Бесшапов Алексей Павлович",
            "Боссерт Александра Максимовна",
            "Гладких Иван Дмитриевич",
            "Гусев Андрей Вадимович",
            "Елушов Егор Валерьевич"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleInterface inferface = new ConsoleInterface(students);
        String command = "";
        inferface.PrintHelp();
        while (!Objects.equals(command, "exit")) {
            command = reader.readLine();
            if (command.equals("/h")) {
                inferface.PrintHelp();
            } else if (Objects.equals(command, "/r")) {
                String student = inferface.GetRandomStudent();
                String answer = "";
                answer = reader.readLine();
                if (answer.equals("y")) {
                    inferface.GetStudentsMark(student);
                }
            } else if (command.equals("/l")) {
                inferface.GetMarkedStudentList();
            }
        }
    }
}