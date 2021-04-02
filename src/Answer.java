import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Answer {
    public static int[][] array = new int[256][256];
    public static int LENGTH = 0;
    public static final String FILE_NAME = "example.txt";
    public static int sum = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(" ");
            for (int i=0; i<line.length; i++) {
                array[LENGTH][i] = Integer.parseInt(line[i]);
            }
            LENGTH++;
        }

        int sum = findMaxSum(0,0);
        System.out.println("sum: " + sum);
    }

    public static int findMaxSum(int row, int column) {
        if (isPrime(array[row][column])) {
            return sum;
        }

        if (row == LENGTH-1) {
            return array[row][column];
        } else {
            if (isPrime(array[row+1][column]) && isPrime(array[row+1][column+1])) {
                array[row][column] = -1;
                return array[row][column];
            } else {
                return array[row][column] + Math.max(findMaxSum(row + 1, column), findMaxSum(row + 1, column + 1));
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i=2; i<number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
