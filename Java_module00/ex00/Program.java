import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner Myobj = new Scanner(System.in);
    System.out.println("Enter number");

    int number = Myobj.nextInt();
    int n = number;
    int result = 0;
    while (n != 0)
    {
      result += (n%10);
      n = n / 10;
    }
    System.out.println("Result is: " + result);

  }
}