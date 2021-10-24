import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class question_4 {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(long[] arr) {

        //Create a sorted array by ascending order, reduce the array to index (0,4)
        List<Long> miniMaxSumList = (LongStream.of(arr)
                .sorted()
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new)))
                .subList(0,4);

        //Create a sorted array by ascending order, reduce the array to index (1,5)
        List<Long> maxSumList = (LongStream.of(arr)
                .sorted()
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new))
                .subList(1,5));

        //Use streams to sum up the results from the reduced list, and use an in built function to sum the results
        long miniMaxSumResults = miniMaxSumList.stream().reduce((long)0, Long::sum);
        long maxSumResults     = maxSumList.stream().reduce((long) 0, Long::sum);

        System.out.println(miniMaxSumResults + " " + maxSumResults);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        long[] arr = new long[5];

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
