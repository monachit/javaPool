public class UsersArrayList implements UsersList {
    private User[] users;
    private int count;

    public UsersArrayList() {
        this.users = new User[10];
        this.count = 0;
    }

    @Override
    public void addUser(User user) {
        if (count >= users.length) {
            int newSize = users.length + (users.length / 2);

            User[] newUsers = new User[newSize];

            for (int i = 0; i < users.length; i++) {
                newUsers[i] = users[i];
            }

            users = newUsers;
        }

        users[count] = user;
        count++;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < count; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }

        throw new UserNotFoundException(
            "User with ID " + id + " not found."
        );
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " is out of bounds."
            );
        }

        return users[index];
    }

    @Override
    public int getUsersCount() {
        return count;
    }
}