import java.util.Scanner;

import Java_module01.ex03.UserNotFoundException;

public class Program {
    public static void main(String[] args) {
        UsersList list = new UsersArrayList();

        list.addUser(new User("Alice", 1000));
        list.addUser(new User("Bob", 500));
        list.addUser(new User("Charlie", 250));

        System.out.println("Total users: " + list.getUsersCount());

        User found = list.getUserById(2);
        System.out.println("Found by ID 2: " + found.getName());

        User byIndex = list.getUserByIndex(0);
        System.out.println("First in list: " + byIndex.getName());

        try {
            list.getUserById(999);
        } catch (UserNotFoundException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}