package org.example;

import org.example.Model.User;
import org.example.Repositories.TipoConta;
import org.example.Repository.UserRepository;
import org.example.Service.UsuarioService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestUsuarioService {

    private UserRepository repository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setup() {
        repository = new UserRepository();
        usuarioService = new UsuarioService(repository);
    }

    @Test
    @DisplayName("Cadastrar usuário novo com tipo de conta")
    void testeCadastrarUsuario() {
        assertTrue(usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.POUPANCA, "senha"));
        assertNotNull(usuarioService.getUsuario("55566677788"));
    }

    @Test
    @DisplayName("Não cadastrar usuário com CPF duplicado")
    void testeCadastrarUsuarioDuplicado() {
        usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.POUPANCA, "senha");
        assertFalse(usuarioService.cadastrarUsuario("Ana2", "55566677788", TipoConta.CORRENTE, "senha2"));
    }

    @Test
    @DisplayName("Autenticar usuário com CPF e senha corretos")
    void testeAutenticarUsuario() {
        usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.CORRENTE, "senha");
        assertNotNull(usuarioService.authenticate("55566677788", "senha"));
    }

    @Test
    @DisplayName("Falha na autenticação com senha incorreta")
    void testeAutenticarSenhaIncorreta() {
        usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.CORRENTE, "senha");
        assertNull(usuarioService.authenticate("55566677788", "errada"));
    }

    @Test
    @DisplayName("Atualizar usuário existente")
    void testeAtualizarUsuario() {
        usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.CORRENTE, "senha");
        User usuario = usuarioService.getUsuario("55566677788");
        usuario.setName("Ana Atualizada");
        assertTrue(usuarioService.atualizarUsuario("55566677788", usuario));
        assertEquals("Ana Atualizada", usuarioService.getUsuario("55566677788").getName());
    }

    @Test
    @DisplayName("Deletar usuário existente")
    void testeDeletarUsuario() {
        usuarioService.cadastrarUsuario("Ana", "55566677788", TipoConta.CORRENTE, "senha");
        assertTrue(usuarioService.deletarUsuario("55566677788"));
        assertNull(usuarioService.getUsuario("55566677788"));
    }
}
