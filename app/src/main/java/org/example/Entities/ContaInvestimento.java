package org.example.Entities;

import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(String titular, Usuario usuario, String numeroConta) {
        super(titular, usuario, numeroConta);
        this.tipoConta = TipoConta.INVESTIMENTO;
    }

    //Método de depósito que atualiza o saldo da conta
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Saldo atual: " + saldo);
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0) {
            saldo -= valor;
            System.out.println("Saldo atual: " + saldo);
        }
        else {
            System.out.println("Valor de saque inválido.");
        }
    }

    public void investir(Investimento investimento) {
        if (investimento.getValor() > 0 && investimento.getValor() <= saldo) {
            saldo -= investimento.getValor();
            // Lógica para investir o valor, como adicionar a uma carteira de investimentos
        } else {
            System.out.println("Valor de investimento inválido ou saldo insuficiente.");
        }
    }

}