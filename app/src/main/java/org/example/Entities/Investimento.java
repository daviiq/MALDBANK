package org.example.Entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Investimento {
    private String nome;
    private double valor;
    LocalDate dataDeRetirada;
    double retorno;

    public Investimento(String nome, double valor, LocalDate dataDeRetirada) {
        this.nome = nome;
        this.valor = valor;
        this.dataDeRetirada = dataDeRetirada;
    }


    //GETTERS e SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(LocalDate dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }

    public double getRetorno() {
        return retorno;
    }

    public void setRetorno(double retorno) {
        this.retorno = retorno;
    }

    public double calcularRetorno() {
        if ("CDB".equals(nome)) {
            LocalDate data = LocalDate.now();
            long dias = ChronoUnit.DAYS.between(data, dataDeRetirada);
            if (dias < 0) {
                System.out.println("Data inválida");
            } else {
                retorno = (valor * 0.000332) * dias;
            }

        } else if ("Tesouro Direto".equals(nome)) {
            LocalDate data = LocalDate.now();
            long dias = ChronoUnit.DAYS.between(data, dataDeRetirada);
            if (dias < 0) {
                System.out.println("Data inválida");
            } else {
                retorno = (valor * 0.000529) * dias;
            }
        } else if ("IBV".equals(nome)) {
            LocalDate data = LocalDate.now();
            long dias = ChronoUnit.DAYS.between(data, dataDeRetirada);
            if (dias < 0) {
                System.out.println("Data inválida");
            } else {
                retorno = (valor * 0.000812) * dias;
            }
        } else if ("FII".equals(nome)) {
            LocalDate data = LocalDate.now();
            long dias = ChronoUnit.DAYS.between(data, dataDeRetirada);
            if (dias < 0) {
                System.out.println("Data inválida");
            } else {
                retorno = (valor * 0.000302) * dias;
            }
        } else if ("LCI".equals(nome)) {
            LocalDate data = LocalDate.now();
            long dias = ChronoUnit.DAYS.between(data, dataDeRetirada);
            if (dias < 0) {
                System.out.println("Data inválida");
            } else {
                retorno = (valor * 0.000274) * dias;
            }
        }
        return retorno;
    }
}