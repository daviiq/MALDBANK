// package org.example;

// import org.example.Model.User;
// import org.example.Repositories.TipoConta;
// import org.junit.jupiter.api.*;
// import static org.junit.jupiter.api.Assertions.*;

// class TesteUsuario {

//     private User user;

//     @BeforeEach
//     void setup() {
//         user = new User("Exemplo", "123", "senha");
//         user.setTipoDeConta(TipoConta.CORRENTE);
//     }

//     // Testes da classe Usuário.
//     @Test
//     @DisplayName("Testa se o nome está sendo inserido no usuário.")
//     void testeNomeUsuario() {
//         usuario.setNome("Exemplo");
//         assertEquals("Exemplo", usuario.getNome());
//     }

//     @Test
//     @DisplayName("Testa se o CPF está sendo inserido no usuário.")
//     void testeCPFUsuario() {
//         usuario.setCPF("123");
//         assertEquals("123", usuario.getCPF());
//     }

//     @Test
//     @DisplayName("Testa se a idade está sendo inserida no usuário.")
//     void testeIdadeUsuario() {
//         usuario.setIdade(10);
//         assertEquals(10, usuario.getIdade());
//     }

//     @Test
//     @DisplayName("Testa se o telefone está sendo inserido no usuário.")
//     void testeTelefoneUsuario() {
//         usuario.setTelefone("123");
//         assertEquals("123", usuario.getTelefone());
//     }

//     @Test
//     @DisplayName("Testa se o email está sendo inserido no usuário.")
//     void testeEmailUsuario() {
//         usuario.setEmail("lucas@gmail.com");
//         assertEquals("lucas@gmail.com", usuario.getEmail());
//     }
// }