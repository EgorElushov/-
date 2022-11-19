import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StudentSelector {
    private final HashMap<String, Integer> studentList;

    public StudentSelector(String[] students) {
        studentList = new HashMap<>();
        for (String student: students) {
            studentList.put(student, -1);
        }
    }

    public String GetRandomStudent() {
        var students = studentList.keySet().toArray();
        int randomStudent = new Random().nextInt(students.length);
        return (String) students[randomStudent];
    }

    public void SetMark(String student, Integer mark) {
        studentList.replace(student, mark);
    }

    public HashMap<String, Integer>  GetMarkedStudentList() {
        HashMap<String, Integer> students = new HashMap<>();
        var studentsArray = studentList.keySet().toArray();
        for (var student: studentsArray) {
            if (studentList.get(student) != -1)
                students.put((String) student, studentList.get(student));
        }
        return students;
    }
}
