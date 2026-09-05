package Java_module01.ex04;

import java.util.UUID;

public class TransactionsService {

    private final UsersList users;

    public TransactionsService(UsersList users) {
        if (users == null) {
            throw new IllegalArgumentException("UsersList cannot be null");
        }
        this.users = users;
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.addUser(user);
    }

    public int getUserBalance(int userId) {
        return users.getUserById(userId).getBalance();
    }

    public void performTransfer(int senderId, int recipientId, int amount) {

        User sender = users.getUserById(senderId);
        User recipient = users.getUserById(recipientId);

        if (amount <= 0) {
            throw new IllegalTransactionException("Amount must be positive");
        }
        if (senderId == recipientId) {
            throw new IllegalTransactionException("Sender and recipient must be different");
        }
        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Insufficient balance for user " + senderId);
        }

        UUID identifier = UUID.randomUUID();

        Transaction debit = new Transaction(identifier, sender, recipient, TransferCategory.DEBIT, amount);
        Transaction credit = new Transaction(identifier, sender, recipient, TransferCategory.CREDIT, amount);

        sender.getTransactions().addTransaction(debit);
        recipient.getTransactions().addTransaction(credit);

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public Transaction[] getUserTransactions(int userId) {
        return users.getUserById(userId).getTransactions().toArray();
    }

    public void removeUserTransaction(int userId, UUID transactionId) {
        users.getUserById(userId).getTransactions().removeTransaction(transactionId);
    }

    public Transaction[] checkTransactionsValidity() {

        Transaction[] all = collectAllTransactions();
        Transaction[] buffer = new Transaction[all.length];
        int unpairedCount = 0;

        for (int i = 0; i < all.length; i++) {
            int occurrences = 0;

            for (int j = 0; j < all.length; j++) {
                if (all[i].getIdentifier().equals(all[j].getIdentifier())) {
                    occurrences++;
                }
            }

            if (occurrences != 2) {
                buffer[unpairedCount] = all[i];
                unpairedCount++;
            }
        }

        Transaction[] result = new Transaction[unpairedCount];
        for (int i = 0; i < unpairedCount; i++) {
            result[i] = buffer[i];
        }
        return result;
    }

    private Transaction[] collectAllTransactions() {

        int total = 0;
        for (int i = 0; i < users.getUsersCount(); i++) {
            total += users.getUserByIndex(i).getTransactions().getTransactionsCount();
        }

        Transaction[] all = new Transaction[total];
        int index = 0;

        for (int i = 0; i < users.getUsersCount(); i++) {
            Transaction[] userTransactions = users.getUserByIndex(i).getTransactions().toArray();
            for (int j = 0; j < userTransactions.length; j++) {
                all[index] = userTransactions[j];
                index++;
            }
        }

        return all;
    }
}