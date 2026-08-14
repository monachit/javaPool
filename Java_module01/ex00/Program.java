import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Alice", 1000);
        User user2 = new User(2, "Bob", 500);

        Transaction transaction1 = new Transaction(user2, user1, -200);
        // Transaction transaction2 = new Transaction(user1, user2, -100);

        System.out.println("Transaction ID: " + transaction1.getIdentifier());
        System.out.println("Sender: " + transaction1.getSender().getName() + ", Amount: " + transaction1.getAmount() + ", Recipient: " + transaction1.getRecipient().getName());
        System.out.println("Sender's new balance: " + transaction1.getSender().getBalance());
        System.out.println("Recipient's new balance: " + transaction1.getRecipient().getBalance());     

        
    }
}