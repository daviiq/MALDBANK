package org.example.Model;

import org.example.Repository.TipoConta;
import org.example.Repository.Usuario;

public abstract class Conta {
    protected double saldo = 0;
    protected int numeroConta;
    protected String titular;
    protected Usuario usuario;
    protected TipoConta tipoConta;

    public Conta(String titular, Usuario usuario, int numeroConta) {
        this.titular = titular;
        this.usuario = usuario;
        this.numeroConta = numeroConta;
        //Coloque aqui o método depositar para atualizar o saldo
        //depositar();
    }

    //GETTERS e SETTERS
    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    //Método de depósito que atualiza o saldo da conta
    //public void depositar(double valor) {}
}