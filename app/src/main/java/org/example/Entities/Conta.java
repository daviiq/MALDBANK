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
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
}