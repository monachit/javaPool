import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;

        while (true) 
        {
            int number = sc.nextInt();

            if (number == 42)
                break;

            int sum = 0;
            int n = number;
            while (n != 0)
            {
                sum += n % 10;
                n = n / 10;
            }
            boolean isPrime = true;
            if (sum <= 1)
                isPrime = false;

            for (int i = 2; i * i <= sum; i++)
            {
                if (sum % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                count++;
        }

        System.out.println("Count of coffee-request : " + count);
    }
}