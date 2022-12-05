import java.io.Console;

public class Main {
    public static boolean is_prime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if (is_prime(i)) {
                System.out.println(i);
            }
        }
    }
}