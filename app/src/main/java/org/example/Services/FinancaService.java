package org.example.Services;

import org.example.Entities.Investimento;
import org.example.Entities.TipoTransacao;
import org.example.Entities.Transacao;
import org.example.Model.User;
import org.example.Repository.UserRepository;

public class FinancaService {
    private final UserRepository userRepository;

    public FinancaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean depositar(String cpf, double valor) {
        if (valor <= 0) {
            return false;
        }

        User usuario = userRepository.getUser(cpf);
        if (usuario == null) {
            return false;
        }

        usuario.deposit(valor);
        usuario.addTransacao(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito em conta"));
        userRepository.saveUser(usuario);
        return true;
    }

    public boolean sacar(String cpf, double valor) {
        if (valor <= 0) {
            return false;
        }

        User usuario = userRepository.getUser(cpf);
        if (usuario == null) {
            return false;
        }

        if (!usuario.withdraw(valor)) {
            return false;
        }

        usuario.addTransacao(new Transacao(TipoTransacao.SAQUE, valor, "Saque em conta"));
        userRepository.saveUser(usuario);
        return true;
    }

    public boolean transferir(String deCpf, String paraCpf, double valor) {
        if (valor <= 0 || deCpf == null || paraCpf == null || deCpf.equals(paraCpf)) {
            return false;
        }

        User remetente = userRepository.getUser(deCpf);
        User destinatario = userRepository.getUser(paraCpf);

        if (remetente == null || destinatario == null) {
            return false;
        }

        if (!remetente.withdraw(valor)) {
            return false;
        }

        destinatario.deposit(valor);
        remetente.addTransacao(new Transacao(TipoTransacao.PIX_ENVIADO, valor, "PIX enviado para " + paraCpf));
        destinatario.addTransacao(new Transacao(TipoTransacao.PIX_RECEBIDO, valor, "PIX recebido de " + deCpf));
        userRepository.saveUser(remetente);
        userRepository.saveUser(destinatario);
        return true;
    }

    public boolean investir(String cpf, Investimento investimento) {
        if (investimento == null || investimento.getValor() <= 0) {
            return false;
        }

        User usuario = userRepository.getUser(cpf);
        if (usuario == null) {
            return false;
        }

        if (!usuario.withdraw(investimento.getValor())) {
            return false;
        }

        investimento.calcularRetorno();
        usuario.addInvestimento(investimento);
        usuario.addTransacao(new Transacao(TipoTransacao.INVESTIMENTO, investimento.getValor(), "Investimento em " + investimento.getNome()));
        userRepository.saveUser(usuario);
        return true;
    }
}
