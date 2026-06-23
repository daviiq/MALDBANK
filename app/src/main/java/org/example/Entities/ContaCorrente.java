package org.example.Entities;

import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;

public class ContaCorrente extends Conta {

    public ContaCorrente(String titular, Usuario usuario, String numeroConta) {
        super(titular, usuario, numeroConta);
        this.tipoConta = TipoConta.CORRENTE;
    }

    //Método de depósito que atualiza o saldo da conta
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

   public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }

}
