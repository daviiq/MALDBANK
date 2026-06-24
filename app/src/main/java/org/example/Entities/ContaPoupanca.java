package org.example.Entities;

import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, Usuario usuario, String numeroConta) {
        super(titular, usuario, numeroConta);
        this.tipoConta = TipoConta.POUPANCA;
    }
    
    @Override
    public void setNumeroConta(String numeroConta) {
        super.setNumeroConta(numeroConta);
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    //Método de depósito que atualiza o saldo da conta
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void renderJuros(double taxa) {
        if (taxa > 0) {
            saldo += saldo * taxa;
        } else {
            System.out.println("Taxa de juros inválida.");
        }
    }

}
