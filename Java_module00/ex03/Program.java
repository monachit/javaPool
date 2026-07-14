import java.util.Scanner;

/*$> java Program
-> Week 1
-> 4 5 2 4 2
-> Week 2
-> 7 7 7 7 6
-> Week 3
-> 4 3 4 9 8
-> Week 4
-> 9 9 4 6 7
-> 42
Week 1 ==>
Week 2 ======>
Week 3 ===>
Week 4 ====> */
public class Program{

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int [] SaveScores = new int[18];
       int count = 0;

       while (true)
       {
            if (!sc.hasNext())
            {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            String token = sc.next();               // read "Week" or "42"
            if (token.equals("42"))
                break;
            if (!token.equalsIgnoreCase("Week") || !sc.hasNextInt())
            {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            int number = sc.nextInt();
            if (number < 1 || number > 18 || number != count + 1)
            {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            int [] scores = new int[5];
            for (int i = 0; i < 5; i++)
            {
                if (!sc.hasNextInt())
                {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                scores[i] = sc.nextInt();
                if (scores[i] < 1 || scores[i] > 9)
                {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
            }
            SaveScores[count] = getMin(scores);
            count++;
       }
       sc.close();
       printChart(SaveScores, count);

    }

    private static void printChart(int[] scores, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("Week " + (i + 1) + " ");
            for (int j = 0; j < scores[i]; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    private static int getMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }
}