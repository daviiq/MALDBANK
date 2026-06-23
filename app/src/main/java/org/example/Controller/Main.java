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

        while (true) {
            System.out.println("Escolha qual tipo de conta você quer: ");
            System.out.println("1 - Conta Corrente.");
            System.out.println("2 - Conta Investimento.");
            System.out.println("3 - Conta Poupança.");
            System.out.println("4 - Conta Salário.");
            System.out.println("0 - Sair.");

            int escolhaConta = scanner.nextInt();

            switch (escolhaConta) {
                case 0:
                    System.out.println("Saindo...");
                    return;
                case 2:
                    Random random = new Random();
                    StringBuilder numeroContaRandom = new StringBuilder();
                    int quantidadeDigitos = 9;

                    for (int i = 0; i < quantidadeDigitos; i++) {
                        int digito = random.nextInt(10);
                        numeroContaRandom.append(digito);
                    }

                    String numeroConta = numeroContaRandom.toString();

                    ContaInvestimento contaInvestimento = new ContaInvestimento(usuario.getNome(), usuario, numeroConta);

                    boolean voltar = false;
                    while (!voltar) {
                        System.out.println("Digite 0 para voltar.");
                        System.out.println("Escolha as opções: ");
                        System.out.println("1 - Depositar");
                        System.out.println("2 - Sacar.");
                        System.out.println("3 - Investir.");

                        int opcoesContaInvestimento = scanner.nextInt();

                        switch (opcoesContaInvestimento) {
                            case 0:
                                voltar = true;
                                break;
                            case 1:
                                System.out.println("Seu saldo é de R$ " + contaInvestimento.getSaldo());
                                System.out.println("Quanto deseja depositar?: ");
                                double deposito = scanner.nextDouble();
                                contaInvestimento.depositar(deposito);
                                System.out.println("Você depositou R$ " + deposito + " e tem R$ " + contaInvestimento.getSaldo()
                                        + " de saldo.");
                                break;
                            case 2:
                                System.out.println("Seu saldo é de R$ " + contaInvestimento.getSaldo());
                                System.out.println("Quanto deseja sacar?: ");
                                double saque = scanner.nextDouble();
                                contaInvestimento.sacar(saque);
                                System.out.println("Você sacou R$ " + saque + " e tem R$ " + contaInvestimento.getSaldo()
                                        + " de saldo.");
                                break;
                            case 3:
                                System.out.println("Escolha os tipos de investimento: ");
                                System.out.println("1 - CDB.");
                                System.out.println("2 - Tesouro Direto.");
                                System.out.println("3 - IBV.");
                                System.out.println("4 - FCI.");
                                System.out.println("5 - LCI.");

                                int escolhaInvestimento = scanner.nextInt();

                                switch (escolhaInvestimento) {
                                    case 1:
                                        calcularInvestimento("CDB", contaInvestimento);
                                        break;
                                    case 2:
                                        calcularInvestimento("Tesouro Direto", contaInvestimento);
                                        break;
                                    case 3:
                                        calcularInvestimento("IBV", contaInvestimento);
                                        break;
                                    case 4:
                                        calcularInvestimento("FCI", contaInvestimento);
                                        break;
                                    case 5:
                                        calcularInvestimento("LCI", contaInvestimento);
                                        break;
                                }
                                break;
                        }
                    }
                    break;
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