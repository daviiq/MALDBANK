package org.example;

import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestUserRepository {

    private UserRepository repository;

    @BeforeEach
    void setup() {
        repository = new UserRepository();
    }

    @Test
    @DisplayName("Salvar e recuperar usuário pelo CPF")
    void testeSalvarEBuscarUsuario() {
        User user = new User("João", "11122233344", "senha");
        repository.saveUser(user);

        User encontrado = repository.getUser("11122233344");
        assertNotNull(encontrado);
        assertEquals("João", encontrado.getName());
    }

    @Test
    @DisplayName("Atualizar usuário existente")
    void testeAtualizarUsuario() {
        User user = new User("João", "11122233344", "senha");
        repository.saveUser(user);
        user.setName("João Atualizado");

        assertTrue(repository.updateUser("11122233344", user));
        assertEquals("João Atualizado", repository.getUser("11122233344").getName());
    }

    @Test
    @DisplayName("Excluir usuário existente")
    void testeDeletarUsuario() {
        User user = new User("João", "11122233344", "senha");
        repository.saveUser(user);

        assertTrue(repository.deleteUser("11122233344"));
        assertNull(repository.getUser("11122233344"));
    }

    @Test
    @DisplayName("Buscar usuário inexistente retorna null")
    void testeBuscarUsuarioInexistente() {
        assertNull(repository.getUser("00000000000"));
    }
}
