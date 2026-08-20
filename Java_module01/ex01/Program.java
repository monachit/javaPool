import java.util.UUID;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Alice", 1000);
        User user2 = new User("Bob", 500);
        User user3 = new User("Charlie", 250);

        System.out.println(user1.getId() + " - " + user1.getName());
        System.out.println(user2.getId() + " - " + user2.getName());
        System.out.println(user3.getId() + " - " + user3.getName());
    }
}