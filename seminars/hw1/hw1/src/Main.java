public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 1000);
        }
        int max = 0, min = 1000;
        double average = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
            if (min > arr[i])
                min = arr[i];
            average += arr[i];
        }
        average /= arr.length;
        System.out.println("max = " + max + "\nmin = " + min + "\naverage = " + average);
    }
}