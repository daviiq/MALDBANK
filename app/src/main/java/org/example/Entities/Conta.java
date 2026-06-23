package org.example.Entities;

import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;

public abstract class Conta {
    protected double saldo = 0;
    protected String numeroConta;
    protected String titular;
    protected Usuario usuario;
    protected TipoConta tipoConta;

    public Conta(String titular, Usuario usuario, String numeroConta) {
        this.titular = titular;
        this.usuario = usuario;
        this.numeroConta = numeroConta;
        //Coloque aqui o método depositar para atualizar o saldo
        //depositar();
    }

    //GETTERS e SETTERS
    public String getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    //Método de depósito que atualiza o saldo da conta
    //public void depositar(double valor) {}
}