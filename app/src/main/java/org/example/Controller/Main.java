package org.example.Controller;

import java.util.Scanner;

import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        UserRepository userRepository = new UserRepository();
        UsuarioService usuarioService = new UsuarioService(userRepository);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Atualizar usuário");
            System.out.println("3. Deletar usuário");
            System.out.println("4. Visualizar usuário");
            System.out.println("5. Sair");

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
                System.out.println("Opção inválida. Digite um número de 1 a 4.");
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
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
