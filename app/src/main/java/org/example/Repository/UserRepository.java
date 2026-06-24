package org.example.Repository;

import org.example.Model.User;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private String tipoDeConta;
    private Map<String, User> users = new HashMap<>();

    // public void User(String tipoDeConta) {
    //     this.tipoDeConta = tipoDeConta;
    //     this.users = new HashMap<>();
    // }



    public String getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(String tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public User newUser(User user, String tipoDeconta) {

        //User user = new User(name, cpf, password);
        //user.setTipoDeConta(tipoDeconta);
        user.setTipoDeConta(tipoDeconta);
        users.put(tipoDeconta, user);
        System.out.println(user);
        return user;
    }

    public boolean upDateUser(String cpf, User updatedUser) {
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