import java.util.Scanner;

public class Program{
    public static void main(String[] args) {
        Scanner Myobj = new Scanner(System.in);
        System.out.println("Enter number");

        int number = Myobj.nextInt();
        if (number <= 1)
        {
            System.out.println("is not prime " + number);
            return ;
        }
        int n = 2;
        while (n * n <= number)
        {
            if (number % n == 0)
            {
                System.out.println("is not prime " + number);
                return ;
            }
            n++;
        }
        System.out.println("is prime " + number);
    }
}