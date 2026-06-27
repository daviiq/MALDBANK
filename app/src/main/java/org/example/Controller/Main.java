package org.example.Controller;

import java.time.LocalDate;
import java.util.Scanner;
import org.example.Entities.Investimento;
import org.example.Entities.User;
import org.example.Repositories.UserRepository;
import org.example.Repositories.TipoConta;
import org.example.Services.ConsoleUtil;
import org.example.Services.FinancaService;
import org.example.Services.UsuarioService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        UsuarioService usuarioService = new UsuarioService(userRepository);
        FinancaService financaService = new FinancaService(userRepository);

        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarUsuario(scanner, usuarioService);
                    break;
                case "2":
                    User usuarioLogado = login(scanner, usuarioService);
                    if (usuarioLogado != null) {
                        executarSessaoUsuario(scanner, usuarioService, financaService, usuarioLogado);
                    }
                    break;
                case "3":
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Digite 1, 2 ou 3.");
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== Bem-vindo ao MALDBANK ===");
        System.out.println("1. Cadastre-se");
        System.out.println("2. Entrar");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarUsuario(Scanner scanner, UsuarioService usuarioService) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String password = scanner.nextLine();

        TipoConta tipoConta = escolherTipoConta(scanner, true);
        if (tipoConta == null) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        if (usuarioService.cadastrarUsuario(nome, cpf, tipoConta, password)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um usuário cadastrado com esse CPF.");
        }
    }

    private static User login(Scanner scanner, UsuarioService usuarioService) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        User usuario = usuarioService.authenticate(cpf, senha);
        if (usuario == null) {
            System.out.println("CPF ou senha incorretos.");
        } else {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + usuario.getName() + "!");
        }
        return usuario;
    }

    private static void executarSessaoUsuario(Scanner scanner, UsuarioService usuarioService, FinancaService financaService, User usuarioLogado) {
        boolean sessaoAtiva = true;

        while (sessaoAtiva) {
            ConsoleUtil.limparTela();
            exibirMenuUsuario();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    visualizarPerfil(usuarioLogado);
                    break;
                case "2":
                    atualizarPerfil(scanner, usuarioService, usuarioLogado);
                    break;
                case "3":
                    sessaoAtiva = deletarPerfil(scanner, usuarioService, usuarioLogado);
                    break;
                case "4":
                    depositar(scanner, financaService, usuarioLogado);
                    break;
                case "5":
                    sacar(scanner, financaService, usuarioLogado);
                    break;
                case "6":
                    transferir(scanner, financaService, usuarioLogado);
                    break;
                case "7":
                    mostrarHistorico(usuarioLogado);
                    break;
                case "8":
                    investir(scanner, financaService, usuarioLogado);
                    break;
                case "9":
                    mostrarInvestimentos(usuarioLogado);
                    break;
                case "10":
                    visualizarSaldo(usuarioLogado);
                    break;
                case "11":
                    System.out.println("Logout realizado.");
                    sessaoAtiva = false;
                    break;
                default:
                    System.out.println("Opção inválida. Digite um número entre 1 e 11.");
            }

            if (sessaoAtiva && !opcao.equals("3")) {
                pressionarEnterParaContinuar(scanner);
            }
        }
    }

    private static void exibirMenuUsuario() {
        System.out.println("\n--- Menu do Usuário ---");
        System.out.println("Opções de cadastro:");
        System.out.println("1. Visualizar cadastro");
        System.out.println("2. Atualizar cadastro");
        System.out.println("3. Deletar cadastro");
        System.out.println("\nOperações financeiras:");
        System.out.println("4. Depositar");
        System.out.println("5. Sacar");
        System.out.println("6. Transferir por CPF");
        System.out.println("7. Histórico de movimentações");
        System.out.println("8. Investir");
        System.out.println("9. Ver investimentos");
        System.out.println("10. Visualizar saldo");
        System.out.println("11. Logout");
        System.out.print("Escolha uma opção: ");
    }

    private static void visualizarPerfil(User usuario) {
        System.out.println("\n--- Dados do Usuário ---");
        System.out.println("Nome: " + usuario.getName());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Tipo de Conta: " + usuario.getTipoDeConta());
        System.out.println("Saldo: R$ " + String.format("%.2f", usuario.getSaldo()));
    }

    private static void atualizarPerfil(Scanner scanner, UsuarioService usuarioService, User usuarioLogado) {
        System.out.print("Digite o novo nome (ou Enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) {
            usuarioLogado.setName(nome);
        }

        System.out.print("Digite a nova senha (ou Enter para manter): ");
        String senha = scanner.nextLine();
        if (!senha.isBlank()) {
            usuarioLogado.setPassword(senha);
        }

        TipoConta novoTipoConta = escolherTipoConta(scanner, false);
        if (novoTipoConta != null) {
            usuarioLogado.setTipoDeConta(novoTipoConta);
        }

        if (usuarioService.atualizarUsuario(usuarioLogado.getCpf(), usuarioLogado)) {
            System.out.println("Cadastro atualizado com sucesso.");
        } else {
            System.out.println("Erro ao atualizar cadastro.");
        }
    }

    private static boolean deletarPerfil(Scanner scanner, UsuarioService usuarioService, User usuarioLogado) {
        System.out.print("Tem certeza que deseja deletar sua conta? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        if (resposta.equals("S")) {
            if (usuarioService.deletarUsuario(usuarioLogado.getCpf())) {
                System.out.println("Conta deletada com sucesso.");
                return false;
            }
            System.out.println("Erro ao deletar a conta.");
            return false;
        }
        System.out.println("Operação cancelada.");
        return true;
    }

    private static void depositar(Scanner scanner, FinancaService financaService, User usuarioLogado) {
        System.out.print("Digite o valor para depositar: ");
        String valorTexto = scanner.nextLine();
        double valor;
        try {
            valor = Double.parseDouble(valorTexto.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        if (financaService.depositar(usuarioLogado.getCpf(), valor)) {
            System.out.printf("Depósito de R$ %.2f realizado com sucesso.%n", valor);
        } else {
            System.out.println("Falha no depósito. Verifique o valor.");
        }
    }

    private static void sacar(Scanner scanner, FinancaService financaService, User usuarioLogado) {
        System.out.print("Digite o valor para saque: ");
        String valorTexto = scanner.nextLine();
        double valor;
        try {
            valor = Double.parseDouble(valorTexto.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        if (financaService.sacar(usuarioLogado.getCpf(), valor)) {
            System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
        } else {
            System.out.println("Falha no saque. Verifique o saldo ou o valor.");
        }
    }

    private static void visualizarSaldo(User usuario) {
        System.out.println("Saldo atual: R$ " + String.format("%.2f", usuario.getSaldo()));
    }

    private static void transferir(Scanner scanner, FinancaService financaService, User usuarioLogado) {
        System.out.print("Digite o CPF do destinatário: ");
        String cpfDestino = scanner.nextLine();
        if (cpfDestino.equals(usuarioLogado.getCpf())) {
            System.out.println("Não é possível transferir para o mesmo CPF.");
            return;
        }

        System.out.print("Digite o valor a transferir: ");
        String valorTexto = scanner.nextLine();
        double valor;
        try {
            valor = Double.parseDouble(valorTexto.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        if (financaService.transferir(usuarioLogado.getCpf(), cpfDestino, valor)) {
            System.out.printf("Transferência de R$ %.2f realizada com sucesso para %s.%n", valor, cpfDestino);
        } else {
            System.out.println("Falha na transferência. Verifique o CPF do destinatário, o saldo ou o valor.");
        }
    }

    private static void mostrarHistorico(User usuario) {
        System.out.println("\n--- Histórico de Movimentações ---");
        if (usuario.getTransacoes().isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada.");
            return;
        }

        usuario.getTransacoes().forEach(transacao -> System.out.println(transacao.toString()));
    }

    private static void investir(Scanner scanner, FinancaService financaService, User usuarioLogado) {
        System.out.println("\n--- Investir ---");
        System.out.println("Escolha um produto de investimento:");
        System.out.println("1. CDB");
        System.out.println("2. Tesouro Direto");
        System.out.println("3. IBV");
        System.out.println("4. FII");
        System.out.println("5. LCI");
        System.out.print("Opção: ");

        String opcao = scanner.nextLine();
        String nomeInvestimento;
        switch (opcao) {
            case "1":
                nomeInvestimento = "CDB";
                break;
            case "2":
                nomeInvestimento = "Tesouro Direto";
                break;
            case "3":
                nomeInvestimento = "IBV";
                break;
            case "4":
                nomeInvestimento = "FII";
                break;
            case "5":
                nomeInvestimento = "LCI";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.print("Digite o valor a investir: ");
        double valorInvestido;
        try {
            valorInvestido = Double.parseDouble(scanner.nextLine().replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        System.out.print("Digite a data de resgate (YYYY-MM-DD): ");
        String dataResgateTexto = scanner.nextLine();
        LocalDate dataResgate;
        try {
            dataResgate = LocalDate.parse(dataResgateTexto);
        } catch (Exception e) {
            System.out.println("Data inválida.");
            return;
        }

        Investimento investimento = new Investimento(nomeInvestimento, valorInvestido, dataResgate);
        if (financaService.investir(usuarioLogado.getCpf(), investimento)) {
            System.out.printf("Investimento de R$ %.2f em %s realizado com sucesso.%n", valorInvestido, nomeInvestimento);
            System.out.printf("Retorno estimado: R$ %.2f%n", investimento.getRetorno());
        } else {
            System.out.println("Falha no investimento. Verifique o saldo ou os dados informados.");
        }
    }

    private static void mostrarInvestimentos(User usuario) {
        System.out.println("\n--- Investimentos ---");
        if (usuario.getInvestimentos().isEmpty()) {
            System.out.println("Nenhum investimento registrado.");
            return;
        }

        usuario.getInvestimentos().forEach(investimento -> {
            System.out.printf("%s - R$ %.2f - Resgate: %s - Retorno estimado: R$ %.2f%n",
                    investimento.getNome(),
                    investimento.getValor(),
                    investimento.getDataDeRetirada(),
                    investimento.getRetorno());
        });
    }

    private static void pressionarEnterParaContinuar(Scanner scanner) {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    private static TipoConta escolherTipoConta(Scanner scanner, boolean permitirCancelar) {
        while (true) {
            System.out.println("Escolha o tipo de conta:");
            System.out.println("1. Conta Corrente");
            System.out.println("2. Conta Investimento");
            System.out.println("3. Conta Poupança");
            System.out.println("4. Conta Salário");
            if (permitirCancelar) {
                System.out.println("5. Cancelar");
            }
            System.out.print("Opção: ");

            String entrada = scanner.nextLine();
            switch (entrada) {
                case "1":
                    return TipoConta.CORRENTE;
                case "2":
                    return TipoConta.INVESTIMENTO;
                case "3":
                    return TipoConta.POUPANCA;
                case "4":
                    return TipoConta.SALARIO;
                case "5":
                    if (permitirCancelar) {
                        return null;
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Digite um número entre 1 e " + (permitirCancelar ? "5" : "4") + ".");
            }
        }
    }
}