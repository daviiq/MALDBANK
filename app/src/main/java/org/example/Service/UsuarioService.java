package org.example.Service;

import org.example.Repository.UserRepository;
import org.example.Model.User;

public class UsuarioService {

    UserRepository userRepository;

    private User usuario;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void cadastrarUsuario(String nome, String CPF, String tipoDeConta, String password) {
        User usuario = new User(nome, CPF, password);
        usuario.setTipoDeConta(tipoDeConta);
        userRepository.newUser(usuario, CPF);
    }

    public void atualizarUsuario(String CPF, User usuarioAtualizado) {
        userRepository.upDateUser(CPF, usuarioAtualizado);
    }

    public void deletarUsuario(String CPF) {
        userRepository.deleteUser(CPF);
    }


    public User getUsuario(String CPF) {
        return userRepository.getUser(CPF);
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
