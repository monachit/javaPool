import java.util.Scanner;
import java.util.UUID;


public class Transaction {
    UUID Identifier;
    User Recipient;
    User Sender;
    int Amount;
    enum category {
        INCOME,
        OUTCOME
    }


    public Transaction(User recipient, User sender, int amount) {

        if (recipient == null || sender == null) {
            System.out.println("Error: Recipient and Sender cannot be null.");
            System.exit(1);
        }

        if (recipient.getIdentifier() == sender.getIdentifier()){
            System.out.println("Error: Recipient and Sender cannot be the same user.");
            System.exit(1);
        }

        if (recipient.getBalance() < amount) {
            System.out.println("Error: Recipient's balance cannot be negative after the transaction.");
            System.exit(1);
        }

        this.Identifier = UUID.randomUUID();
        this.Recipient = recipient;
        this.Sender = sender;
        this.Amount = amount;

        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance(sender.getBalance() - amount);
    }



    public UUID getIdentifier() {
        return Identifier;
    }

    public User getRecipient() {
        return Recipient;
    }

    public User getSender() {
        return Sender;
    }

    public int getAmount() {
        return Amount;
    }


}