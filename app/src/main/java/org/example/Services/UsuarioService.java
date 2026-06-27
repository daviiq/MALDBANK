package org.example.Services;

import org.example.Repository.UserRepository;
import org.example.Model.User;
import org.example.Repositories.TipoConta;

public class UsuarioService {

    private final UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean cadastrarUsuario(String nome, String cpf, TipoConta tipoDeConta, String password) {
        if (userRepository.getUser(cpf) != null) {
            return false;
        }

        User usuario = new User(nome, cpf, password);
        usuario.setTipoDeConta(tipoDeConta);
        userRepository.saveUser(usuario);
        return true;
    }

    public boolean atualizarUsuario(String cpf, User usuarioAtualizado) {
        return userRepository.updateUser(cpf, usuarioAtualizado);
    }

    public boolean deletarUsuario(String cpf) {
        return userRepository.deleteUser(cpf);
    }

    public User getUsuario(String cpf) {
        return userRepository.getUser(cpf);
    }

    public User authenticate(String cpf, String password) {
        User usuario = userRepository.getUser(cpf);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

}



















// class Usuario {
//     private String nome;
//     private String CPF;
//     private int idade;
//     private int telefone;
//     private String email;

//     public Usuario() {}

//     //GETTERS E SETTERS
//     public int getIdade() {
//         return idade;
//     }
//     public void setIdade(int idade) {
//         this.idade = idade;
//     }

//     public String getCPF() {
//         return CPF;
//     }
//     public void setCPF(String CPF) {
//         this.CPF = CPF;
//     }

//     public String getNome() {
//         return nome;
//     }
//     public void setNome(String nome) {
//         this.nome = nome;
//     }

//     public int getTelefone() {
//         return telefone;
//     }
//     public void setTelefone(int telefone) {
//         this.telefone = telefone;
//     }

//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
// }
