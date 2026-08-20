import java.util.Scanner;

public class UserIdsGenerator{
    
    private static UserIdsGenerator instance;
    private int currentId;

    private UserIdsGenerator() {
        this.currentId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        this.currentId++;
        return this.currentId;
    }
}