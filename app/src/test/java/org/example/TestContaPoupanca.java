package org.example;

import org.example.Entities.ContaPoupanca;
import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestContaPoupanca {

    private Usuario usuario;
    private ContaPoupanca contaPoupanca;

    @BeforeEach
    void setup() {
        usuario = new Usuario();
        contaPoupanca = new ContaPoupanca("login", usuario, "123");
    }

    @Test
    @DisplayName("Testa se o número da conta poupança está sendo inserido.")
    void testeNumeroConta() {
        assertEquals("123", contaPoupanca.getNumeroConta());
    }

    @Test
    @DisplayName("Testa se o saldo está sendo inserido na conta poupança.")
    void testeSaldo() {
        contaPoupanca.setSaldo(10);
        assertEquals(10, contaPoupanca.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo depositar na conta poupança.")
    void testeDeposito() {
        contaPoupanca.depositar(10);
        assertEquals(10, contaPoupanca.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo render juros na conta poupança.")
    void testeRenderJuros() {
        contaPoupanca.depositar(100);
        contaPoupanca.renderJuros(0.1);
        assertEquals(110, contaPoupanca.getSaldo());
    }

    @Test
    @DisplayName("Testa se o tipo da conta é POUPANCA.")
    void testeTipoConta() {
        assertEquals(TipoConta.POUPANCA, contaPoupanca.getTipoConta());
    }
}
