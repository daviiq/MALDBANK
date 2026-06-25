package org.example;

import org.example.Entities.ContaCorrente;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TesteUsuario {

    Usuario usuario = new Usuario("login", "123", 30, "33468613", "email@gmail.com");
    ContaCorrente contaCorrente = new ContaCorrente("login", usuario, "123");

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes.");
    }

    @BeforeEach
    void setup() {
        usuario = new Usuario("login", "123", 30, "33468613", "email@gmail.com");
        contaCorrente = new ContaCorrente("login", usuario, "123");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    // Testes da classe Usuário.
    @Test
    @DisplayName("Testa se o nome está sendo inserido no usuário.")
    void testeNomeUsuario() {
        assertEquals("login", usuario.getNome());
    }

    @Test
    @DisplayName("Testa se o CPF está sendo inserido no usuário.")
    void testeCPFUsuario() {
        assertEquals("123", usuario.getCPF());
    }

    @Test
    @DisplayName("Testa se a idade está sendo inserida no usuário.")
    void testeIdadeUsuario() {
        assertEquals(30, usuario.getIdade());
    }

    @Test
    @DisplayName("Testa se o telefone está sendo inserido no usuário.")
    void testeTelefoneUsuario() {
        assertEquals("33468613", usuario.getTelefone());
    }

    @Test
    @DisplayName("Testa se o email está sendo inserido no usuário.")
    void testeEmailUsuario() {
        assertEquals("email@gmail.com", usuario.getEmail());
    }

}