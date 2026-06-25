package org.example;

import org.example.Entities.ContaSalario;
import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestContaSalario {

    private Usuario usuario;
    private ContaSalario contaSalario;

    @BeforeEach
    void setup() {
        usuario = new Usuario();
        contaSalario = new ContaSalario("login", usuario, "123");
    }

    @Test
    @DisplayName("Testa se o número da conta salário está sendo inserido.")
    void testeNumeroConta() {
        assertEquals("123", contaSalario.getNumeroConta());
    }

    @Test
    @DisplayName("Testa se o saldo está sendo inserido na conta salário.")
    void testeSaldo() {
        contaSalario.setSaldo(10);
        assertEquals(10, contaSalario.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo sacar na conta salário.")
    void testeSaque() {
        contaSalario.setSaldo(20);
        contaSalario.sacar(5);
        assertEquals(15, contaSalario.getSaldo());
    }

    @Test
    @DisplayName("Testa se o tipo da conta é SALARIO.")
    void testeTipoConta() {
        assertEquals(TipoConta.SALARIO, contaSalario.getTipoConta());
    }
}
