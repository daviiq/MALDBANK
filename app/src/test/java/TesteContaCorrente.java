import org.example.Entities.ContaCorrente;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TesteContaCorrente {

    Usuario usuario = new Usuario("login", "123", 30, "33468613", "email@gmail.com");
    ContaCorrente contaCorrente = new ContaCorrente("login", usuario, "123");

    @Test
    @DisplayName("Testa se o número da conta corrente está sendo inserido.")
    void testeNumeroContaCorrente() {
        assertEquals("123", contaCorrente.getNumeroConta());
    }

    @Test
    @DisplayName("Testa se o saldo está sendo inserido.")
    void testeSaldoContaCorrente() {
        contaCorrente.setSaldo(10);
        assertEquals(10, contaCorrente.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo depositar na Conta Corrente.")
    void testeDepositoContaCorrente() {
        contaCorrente.depositar(10);
        assertEquals(10, contaCorrente.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo sacar na Conta Corrente.")
    void testeSaqueContaCorrente() {
        contaCorrente.depositar(10);
        contaCorrente.sacar(5);

    }

}