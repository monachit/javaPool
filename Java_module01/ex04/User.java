package Java_module01.ex04;

public class User {
    private int identifier;
    private String name;
    private double balance;
    private TransactionsList transactions;

    public User(String name, double balance) {
        this.identifier   = UserIdsGenerator.getInstance().generateId();
        this.name         = name;
        this.balance      = checkPositive(balance);
        this.transactions = new TransactionLinkedList();
    }

    private double checkPositive(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return value;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = checkPositive(balance);
    }

    public TransactionsList getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "User{id=" + identifier + ", name=" + name + ", balance=" + balance + "}";
    }
}