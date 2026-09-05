package Java_module01.ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void remove(UUID id);

    Transaction[] toArray();
}
