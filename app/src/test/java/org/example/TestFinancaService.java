package org.example;

import org.example.Entities.Investimento;
import org.example.Entities.TipoTransacao;
import org.example.Entities.User;
import org.example.Repositories.UserRepository;
import org.example.Services.FinancaService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class TestFinancaService {

    private UserRepository repository;
    private FinancaService financaService;
    private User userA;
    private User userB;

    @BeforeEach
    void setup() {
        repository = new UserRepository();
        financaService = new FinancaService(repository);
        userA = new User("Alice", "11122233344", "senha");
        userA.setTipoDeConta(org.example.Repositories.TipoConta.CORRENTE);
        repository.saveUser(userA);

        userB = new User("Bruno", "99988877766", "senha");
        userB.setTipoDeConta(org.example.Repositories.TipoConta.CORRENTE);
        repository.saveUser(userB);
    }

    @Test
    @DisplayName("Depositar atualiza saldo e gera transação")
    void testeDeposito() {
        assertTrue(financaService.depositar("11122233344", 100));
        assertEquals(100.0, repository.getUser("11122233344").getSaldo());
        assertEquals(1, repository.getUser("11122233344").getTransacoes().size());
        assertEquals(TipoTransacao.DEPOSITO, repository.getUser("11122233344").getTransacoes().get(0).getTipo());
    }

    @Test
    @DisplayName("Sacar atualiza saldo e gera transação")
    void testeSaque() {
        financaService.depositar("11122233344", 100);
        assertTrue(financaService.sacar("11122233344", 50));
        assertEquals(50.0, repository.getUser("11122233344").getSaldo());
    }

    @Test
    @DisplayName("Não permite saque maior que o saldo")
    void testeSaqueInsuficiente() {
        financaService.depositar("11122233344", 50);
        assertFalse(financaService.sacar("11122233344", 100));
        assertEquals(50.0, repository.getUser("11122233344").getSaldo());
    }

    @Test
    @DisplayName("Transferência entre usuários gera transações de envio e recebimento")
    void testeTransferencia() {
        financaService.depositar("11122233344", 100);
        assertTrue(financaService.transferir("11122233344", "99988877766", 30));
        assertEquals(70.0, repository.getUser("11122233344").getSaldo());
        assertEquals(30.0, repository.getUser("99988877766").getSaldo());
        assertEquals(TipoTransacao.PIX_ENVIADO, repository.getUser("11122233344").getTransacoes().get(1).getTipo());
        assertEquals(TipoTransacao.PIX_RECEBIDO, repository.getUser("99988877766").getTransacoes().get(0).getTipo());
    }

    @Test
    @DisplayName("Investir reduz saldo e registra investimento")
    void testeInvestimento() {
        financaService.depositar("11122233344", 200);
        Investimento investimento = new Investimento("CDB", 100, LocalDate.now().plusDays(30));
        assertTrue(financaService.investir("11122233344", investimento));
        assertEquals(100.0, repository.getUser("11122233344").getSaldo());
        assertEquals(1, repository.getUser("11122233344").getInvestimentos().size());
    }
}
