package org.example.Model;

import org.example.Entities.Investimento;
import org.example.Entities.Transacao;
import org.example.Repositories.TipoConta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String name;
    private String cpf;
    private String password;
    private TipoConta tipoDeConta;
    private double saldo = 0;
    private final List<Transacao> transacoes = new ArrayList<>();
    private final List<Investimento> investimentos = new ArrayList<>();

    public User(String name, String cpf, String password) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(TipoConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            saldo += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= saldo) {
            saldo -= amount;
            return true;
        }
        return false;
    }

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public void addInvestimento(Investimento investimento) {
        investimentos.add(investimento);
    }

    public List<Investimento> getInvestimentos() {
        return Collections.unmodifiableList(investimentos);
    }
}

