package org.example.Controller;

import org.example.Entities.ContaCorrente;
import org.example.Entities.ContaInvestimento;
import org.example.Entities.Investimento;
import org.example.Repositories.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        UserRepository userRepository = new UserRepository();
        UsuarioService usuarioService = new UsuarioService(userRepository);

        Boolean sistemaInicial = true;

        while (sistemaInicial) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastre-se");
            System.out.println("2. Atualizar cadastro");
            System.out.println("3. Deletar Cadastro");
            System.out.println("4. Visualizar Cadastro");
            System.out.println("5. Criar conta Bancária");
            System.out.println("6. Acessar conta");
            System.out.println("7. Sair");

            if (!scanner.hasNextLine()) {
                System.out.println("Nenhuma entrada recebida. Encerrando...");
                scanner.close();
                return;
            }

            String entrada = scanner.nextLine();
            int opcao;
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número de 1 a 7.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do usuário: ");
                    String CPF = scanner.nextLine();
                    System.out.print("Digite o tipo de conta do usuário: ");
                    String tipoDeConta = scanner.nextLine();
                    System.out.print("Digite a senha do usuário: ");
                    String password = scanner.nextLine();

                    usuarioService.cadastrarUsuario(nome, CPF, tipoDeConta, password);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o CPF do usuário a ser atualizado: ");
                    String cpfAtualizar = scanner.nextLine();
                    User usuarioAtualizado = new User("", "", "");
                    usuarioService.atualizarUsuario(cpfAtualizar, usuarioAtualizado);
                    System.out.println("Usuário atualizado com sucesso!");
                    break;

                case 3:
                    System.out.print("Digite o CPF do usuário a ser deletado: ");
                    String cpfDeletar = scanner.nextLine();
                    usuarioService.deletarUsuario(cpfDeletar);
                    System.out.println("Usuário deletado com sucesso!");
                    break;
                
                case 4:
                    System.out.print("Digite o CPF do usuário a ser visualizado: ");
                    String cpfVisualizar = scanner.nextLine();
                    User usuario = usuarioService.getUsuario(cpfVisualizar);
                    if (usuario != null) {
                        System.out.println("Usuário encontrado:");
                        System.out.println("Nome: " + usuario.getName());
                        System.out.println("CPF: " + usuario.getCpf());
                        System.out.println("Tipo de Conta: " + usuario.getTipoDeConta());
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                    //Criação das Contas
                case 5:

                    Boolean sistemaContas = true;
                    while (sistemaContas) {
                        System.out.println("Qual tipo de conta você deseja criar ?");
                        System.out.println("");
                        System.out.println("1. Conta Corrente");
                        System.out.println("2. Conta Investimento");
                        System.out.println("3. Conta Poupanca");
                        System.out.println("4. Conta Salario");
                        System.out.println("5. Sair");

                        String entrada2 = scanner.nextLine();
                        int opcao2;
                        try {
                            opcao2 = Integer.parseInt(entrada2);
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida. Digite um número de 1 a 5.");
                            continue;
                        }

                        switch (opcao2) {
                            case 1:
                                System.out.println("Digite o titular da conta: ");
                                String nomeTitular = scanner.nextLine();
                                System.out.println("Digite a idade do titular: ");
                                int idadeTitular = scanner.nextInt();
                                System.out.println("Digite o CPF do titular: ");
                                String cpfTitular = scanner.next();
                                System.out.println("Digite o telefone do titular");
                                String telefoneTitular = scanner.next();
                                System.out.println("Digite o email do titular ");
                                String emailTitular = scanner.next();

                                Usuario usuarioCadastrado = new Usuario(nomeTitular,cpfTitular,idadeTitular,telefoneTitular,emailTitular);

                                System.out.println("Os dados a seguir estão corretos ? (S/N)");
                                usuarioCadastrado.imprimirDados();
                                char respostaEscolha = Character.toUpperCase(scanner.next().charAt(0));
                                scanner.nextLine(); // limpa o buffer

                                if (respostaEscolha == 'N') {
                                    break;
                                } else if (respostaEscolha == 'S') {
                                    //Precisa salvar a Conta no repositório para conseguir acessa-la novamente
                                    System.out.println("Conta criada !");
                                    sistemaContas = false;
                                    break;
                                }

                                break;

                                case 2:
                                System.out.println("=== CONTA INVESTIMENTO ===");

                                System.out.println("Digite o titular da conta: ");
                                nomeTitular = scanner.nextLine();

                                System.out.println("Digite a idade do titular: ");
                                idadeTitular = Integer.parseInt(scanner.nextLine());

                                System.out.println("Digite o CPF do titular: ");
                                cpfTitular = scanner.nextLine();

                                System.out.println("Digite o telefone do titular: ");
                                telefoneTitular = scanner.nextLine();

                                System.out.println("Digite o email do titular: ");
                                emailTitular = scanner.nextLine();

                                usuarioCadastrado = new Usuario(
                                        nomeTitular,
                                        cpfTitular,
                                        idadeTitular,
                                        telefoneTitular,
                                        emailTitular
                                );

                                System.out.println("Os dados a seguir estão corretos? (S/N)");
                                usuarioCadastrado.imprimirDados();

                                respostaEscolha = Character.toUpperCase(
                                        scanner.nextLine().charAt(0)
                                );

                                if (respostaEscolha == 'S') {
                                    System.out.println("Conta Investimento criada!");
                                    sistemaContas = false;
                                }

                                break;

                            case 3:
                                System.out.println("=== CONTA POUPANÇA ===");

                                System.out.println("Digite o titular da conta: ");
                                nomeTitular = scanner.nextLine();

                                System.out.println("Digite a idade do titular: ");
                                idadeTitular = Integer.parseInt(scanner.nextLine());

                                System.out.println("Digite o CPF do titular: ");
                                cpfTitular = scanner.nextLine();

                                System.out.println("Digite o telefone do titular: ");
                                telefoneTitular = scanner.nextLine();

                                System.out.println("Digite o email do titular: ");
                                emailTitular = scanner.nextLine();

                                usuarioCadastrado = new Usuario(
                                        nomeTitular,
                                        cpfTitular,
                                        idadeTitular,
                                        telefoneTitular,
                                        emailTitular
                                );

                                System.out.println("Os dados a seguir estão corretos? (S/N)");
                                usuarioCadastrado.imprimirDados();

                                respostaEscolha = Character.toUpperCase(
                                        scanner.nextLine().charAt(0)
                                );

                                if (respostaEscolha == 'S') {
                                    System.out.println("Conta Poupança criada!");
                                    sistemaContas = false;
                                }

                                break;

                            case 4:
                                System.out.println("=== CONTA SALÁRIO ===");

                                System.out.println("Digite o titular da conta: ");
                                nomeTitular = scanner.nextLine();

                                System.out.println("Digite a idade do titular: ");
                                idadeTitular = Integer.parseInt(scanner.nextLine());

                                System.out.println("Digite o CPF do titular: ");
                                cpfTitular = scanner.nextLine();

                                System.out.println("Digite o telefone do titular: ");
                                telefoneTitular = scanner.nextLine();

                                System.out.println("Digite o email do titular: ");
                                emailTitular = scanner.nextLine();

                                usuarioCadastrado = new Usuario(
                                        nomeTitular,
                                        cpfTitular,
                                        idadeTitular,
                                        telefoneTitular,
                                        emailTitular
                                );

                                System.out.println("Os dados a seguir estão corretos? (S/N)");
                                usuarioCadastrado.imprimirDados();

                                respostaEscolha = Character.toUpperCase(
                                        scanner.nextLine().charAt(0)
                                );

                                if (respostaEscolha == 'S') {
                                    System.out.println("Conta Salário criada!");
                                    sistemaContas = false;
                                }

                                break;

                            case 5:
                                System.out.println("Saindo...");
                                sistemaContas = false;
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    sistemaInicial = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}