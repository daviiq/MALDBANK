package org.example.Entities;

import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;

public class ContaSalario extends Conta {
    public ContaSalario(String titular, Usuario usuario, String numeroConta) {
        super(titular, usuario, numeroConta);
        this.tipoConta = TipoConta.SALARIO;
    }

    
    @Override
    public void setNumeroConta(String numeroConta) {
        super.setNumeroConta(numeroConta);
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    public void sacar(double valor) {
        if (valor > 0) {
            saldo -= valor;
        } else {
            System.out.println("Valor de depósito inválido");
        }
    }

}
