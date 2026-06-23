package org.example.Repositories;

public class Usuario {
    private String nome;
    private String CPF;
    private int idade;
    private String telefone;
    private String email;

    public Usuario() {}

    //GETTERS E SETTERS
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void imprimirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Idade: " + idade);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }

}
