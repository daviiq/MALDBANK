package org.example.Repository;

import org.example.Model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public User saveUser(User user) {
        users.put(user.getCpf(), user);
        return user;
    }

    public boolean updateUser(String cpf, User updatedUser) {
        if (users.containsKey(cpf)) {
            users.put(cpf, updatedUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String cpf) {
        if (users.containsKey(cpf)) {
            users.remove(cpf);
            return true;
        }
        return false;
    }

    public User getUser(String cpf) {
        return users.get(cpf);
    }
}
