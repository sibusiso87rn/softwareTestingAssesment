import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class question_3 {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {

        //Create a sorted array by ascending order, reduce the array to index (0,4)
        List<Integer> miniMaxSumList = (IntStream.of(arr)
                .sorted()
                .filter(i -> i >= 1 && i <=1000))
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new))
                .subList(0,4);

        //Create a sorted array by ascending order, reduce the array to index (1,5)
        List<Integer> maxSumList = (IntStream.of(arr)
                .sorted()
                .filter(i -> i >= 1 && i <=1000))
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new))
                .subList(1,5);

        //Use streams to sum up the results from the reduced list, and use an in built function to sum the results
        int miniMaxSumResults = miniMaxSumList.stream().reduce(0, Integer::sum);
        int maxSumResults     = maxSumList.stream().reduce(0, Integer::sum);

        System.out.println("Our minimum sum is " + miniMaxSumList +
                           " = " + miniMaxSumResults + " and our maximum sum is "+ maxSumList  +
                           " = " + maxSumResults + ". \n " + miniMaxSumResults + "\t " + maxSumResults);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}