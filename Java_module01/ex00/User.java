import java.util.Scanner;

public class User {
    int Identifier;
    String Name;
    int Balance;

    public User(int identifier, String name, int balance) {
        this.Identifier = identifier;
        this.Name = name;
        this.Balance = CheckPositive(balance);
    }

    private int CheckPositive(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return value;
    }

    public int getIdentifier() {
        return Identifier;
    }

    public String getName() {
        return Name;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        this.Balance = CheckPositive(balance);
    }
}