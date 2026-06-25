package org.example;

import org.example.Entities.ContaInvestimento;
import org.example.Entities.Investimento;
import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class TestContaInvestimento {

    private Usuario usuario;
    private ContaInvestimento contaInvestimento;

    @BeforeEach
    void setup() {
        usuario = new Usuario();
        contaInvestimento = new ContaInvestimento("login", usuario, "123");
    }

    @Test
    @DisplayName("Testa se o número da conta investimento está sendo inserido.")
    void testeNumeroConta() {
        assertEquals("123", contaInvestimento.getNumeroConta());
    }

    @Test
    @DisplayName("Testa se o saldo está sendo inserido na conta investimento.")
    void testeSaldo() {
        contaInvestimento.setSaldo(10);
        assertEquals(10, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo depositar na conta investimento.")
    void testeDeposito() {
        contaInvestimento.depositar(10);
        assertEquals(10, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo sacar na conta investimento.")
    void testeSaque() {
        contaInvestimento.depositar(20);
        contaInvestimento.sacar(5);
        assertEquals(15, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo realizar um investimento.")
    void testeInvestir() {
        contaInvestimento.depositar(100);
        Investimento investimento = new Investimento("CDB", 50, LocalDate.now().plusDays(30));
        contaInvestimento.investir(investimento);
        assertEquals(50, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se o tipo da conta é INVESTIMENTO.")
    void testeTipoConta() {
        assertEquals(TipoConta.INVESTIMENTO, contaInvestimento.getTipoConta());
    }
}
