import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.UsuarioService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TesteUsuarioService {

    UsuarioService usuarioService;
    UserRepository userRepository;

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes de UsuarioService: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes de UsuarioService.");
    }

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
        usuarioService = new UsuarioService(userRepository);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    @Test
    @DisplayName("Testa o cadastro de um usuário.")
    void testeCadastrarUsuario() {
        usuarioService.cadastrarUsuario("Joao", "12345678900", "CORRENTE", "senha123");
        User usuario = usuarioService.getUsuario("12345678900");
        assertNotNull(usuario);
        assertEquals("Joao", usuario.getName());
        assertEquals("12345678900", usuario.getCpf());
        assertEquals("senha123", usuario.getPassword());
    }

    @Test
    @DisplayName("Testa a atualização de um usuário.")
    void testeAtualizarUsuario() {
        usuarioService.cadastrarUsuario("Joao", "12345678900", "CORRENTE", "senha123");
        User usuarioAtualizado = new User("Joao Silva", "12345678900", "novaSenha");
        usuarioService.atualizarUsuario("12345678900", usuarioAtualizado);
        User resultado = usuarioService.getUsuario("12345678900");
        assertNotNull(resultado);
        assertEquals("Joao Silva", resultado.getName());
        assertEquals("novaSenha", resultado.getPassword());
    }

    @Test
    @DisplayName("Testa a deleção de um usuário.")
    void testeDeletarUsuario() {
        usuarioService.cadastrarUsuario("Joao", "12345678900", "CORRENTE", "senha123");
        usuarioService.deletarUsuario("12345678900");
        assertNull(usuarioService.getUsuario("12345678900"));
    }

    @Test
    @DisplayName("Testa a busca por um CPF inexistente.")
    void testeGetUsuarioInexistente() {
        assertNull(usuarioService.getUsuario("00000000000"));
    }
}
