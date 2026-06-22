package org.example.Controller;

import org.example.Entities.ContaCorrente;
import org.example.Entities.ContaInvestimento;
import org.example.Entities.Investimento;
import org.example.Repositories.Usuario;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Usuario usuario = new Usuario();

        System.out.println("Bem vindo ao MALDBANK! Por favor siga as instruções abaixo para realizar seu cadastro!");

        System.out.println("Escreva seu nome: ");
        String nome = scanner.nextLine();
        usuario.setNome(nome);

        System.out.println("Escreva seu CPF: ");
        String CPF = scanner.nextLine();
        usuario.setCPF(CPF);

        System.out.println("Escreva sua idade: ");
        int idade = scanner.nextInt();
        usuario.setIdade(idade);

        scanner.nextLine();

        System.out.println("Escreva seu telefone: ");
        String telefone = scanner.nextLine();
        usuario.setTelefone(telefone);

        System.out.println("Escreva seu e-mail: ");
        String email = scanner.nextLine();
        usuario.setEmail(email);

        usuario.imprimirDados();

        System.out.println("Escolha qual tipo de conta você quer: ");
        System.out.println("1 - Conta Corrente.");
        System.out.println("2 - Conta Investimento.");
        System.out.println("3 - Conta Poupança.");
        System.out.println("4 - Conta Salário.");

        int escolhaConta = scanner.nextInt();

        if (escolhaConta == 2) {
            Random random = new Random();
            StringBuilder numeroContaRandom = new StringBuilder();
            int quantidadeDigitos = 9;

            for (int i = 0; i < quantidadeDigitos; i++) {
                int digito = random.nextInt(10);
                numeroContaRandom.append(digito);
            }

            String numeroConta = numeroContaRandom.toString();

            ContaInvestimento contaInvestimento = new ContaInvestimento(usuario.getNome(), usuario, numeroConta);

            System.out.println("Escolha os tipos de investimento: ");
            System.out.println("1 - CDB.");
            System.out.println("2 - Tesouro Direto.");
            System.out.println("3 - IBV.");
            System.out.println("4 - FCI.");
            System.out.println("5 - LCI.");

            int escolhaInvestimento = scanner.nextInt();
            if (escolhaInvestimento == 1) {
                calcularInvestimento("CDB", contaInvestimento);
            } else if (escolhaInvestimento == 2) {
                calcularInvestimento("Tesouro Direto", contaInvestimento);
            } else if (escolhaInvestimento == 3) {
                calcularInvestimento("IBV", contaInvestimento);
            } else if (escolhaInvestimento == 4) {
                calcularInvestimento("FCI", contaInvestimento);
            } else if (escolhaInvestimento == 5) {
                calcularInvestimento("LCI", contaInvestimento);
            }
        }
    }

    static void calcularInvestimento(String nome, ContaInvestimento contaInvestimento) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cite o valor que queira investir: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Cite a data de retirada(use formato yyyy-mm-dd): ");
        String dataRetiradaString = scanner.nextLine();
        LocalDate dataRetirada = LocalDate.parse(dataRetiradaString);

        Investimento investimento = new Investimento(nome, valor, dataRetirada);
        System.out.println("O retorno foi de R$: " + investimento.calcularRetorno() + " reais.");

        contaInvestimento.investir(investimento);
    }
}