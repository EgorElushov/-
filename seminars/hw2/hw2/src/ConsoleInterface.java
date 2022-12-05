import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInterface {
    private StudentSelector studentSelector;

    public ConsoleInterface(String[] students) {
        studentSelector = new StudentSelector(students);
    }

    public void PrintHelp() {
        System.out.println("1. /r - choose random student\n" +
                           "2. /l - list of student with grades\n" +
                           "3. exit");
    }

    public String GetRandomStudent() {
        String student = studentSelector.GetRandomStudent();
        System.out.println("Отвечает " + student +
                            ".Присутствует ли на паре? (y/n)");
        return student;
    }

    public void GetStudentsMark(String student) throws IOException {
        System.out.print("Оценка за ответ: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        command = reader.readLine();
        Integer mark = Integer.parseInt(command);
        studentSelector.SetMark(student, mark);
    }

    public void GetMarkedStudentList() {
        var studentList = studentSelector.GetMarkedStudentList();
        var students = studentList.keySet().toArray();
        for (var student: students) {
            System.out.println("Студент: " + student + ". Получил оценку: " + studentList.get((String) student));
        }
    }
}
