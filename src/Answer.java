import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Row {
    //I choose to do it in this way so it would be dynamic to add element to each row
    private ArrayList<Integer> list;

    public Row(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<Integer> getList() {
        return list;
    }
}

public class Answer {
    public static Row[] array;
    public static int currentIndex = 0;

    public static void main(String args[]) throws FileNotFoundException {
        //I didn't use try catch for the FileNotFoundException so the code would be much more readable

        Scanner sc = new Scanner(new BufferedReader(new FileReader("example.txt")));
        //Determine the length of row from the txt file
        int rowLength=0;
        while(sc.hasNextLine()){
            rowLength++;
            sc.nextLine();
        }
        //Re-initializing scanner sc
        sc = new Scanner(new BufferedReader(new FileReader("example.txt")));

        array = new Row[rowLength];
        int counter = 0;
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int i=0; i<line.length; i++) {
                tempList.add(Integer.parseInt(line[i]));
            }
            array[counter] = new Row(new ArrayList<>(tempList));
            counter++;
        }

        //First value of the sum will be the first and only element of the first row
        int sum = array[0].getList().get(0);

        for (int i=0; i< rowLength-1; i++) {
            if (currentIndex==0) {
                sum += determineNeighbourIndex(i+1, 0,1);
            } else {
                sum += determineNeighbourIndex(i+1, currentIndex-1, currentIndex, currentIndex+1);
            }
        }

        System.out.println("Sum: " + sum);
    }

    public static int determineNeighbourIndex(int rowIndex, int... params) {
        int[] paramsArray = params;
        System.out.println(array[rowIndex].getList());
        int maxValue = 0;
        for (int i=0; i<paramsArray.length; i++) {
            if (array[rowIndex].getList().get(paramsArray[i]) > maxValue) {
                //PRIME CONTROL
                if (!isPrime(array[rowIndex].getList().get(paramsArray[i]))) {
                    maxValue = array[rowIndex].getList().get(paramsArray[i]);
                    currentIndex = paramsArray[i];
                }
            }
        }
        return maxValue;
    }

    //Checking if a number is prime or not
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
