package Java_module01.ex03;

import java.util.UUID;

public class TransactionLinkedList implements TransactionsList {

    private Transaction head;
    private Transaction tail;
    private int size;

    public TransactionLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Error: Transaction cannot be null.");
        }

        if (head == null) {
            head = transaction;
            tail = transaction;
        } else {
            tail.setNext(transaction);
        }
        size++;
    }

    @Override
    public void remove(UUID id) {
        if (size == 0)
            throw new IllegalArgumentException("Error: List is empty.");
        else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            Transaction current = head;
            Transaction prev = null;
            while (current != null) {
                if (current.getIdentifier() == id) {
                    if (current.getNext() != null && prev != null)
                        prev.setNext(current.getNext());

                    if (prev == null)
                        head = current.getNext();
                    if (current == tail)
                        tail = prev;

                    return;

                }
                prev = current;
                current = current.getNext();
            }

        }

    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Transaction current = head;
        int i = 0;
        while (current != null) {
            array[i] = current;
            current = current.getNext();
            i++;
        }
        return array;
    }

}