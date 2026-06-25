import org.example.Entities.ContaCorrente;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TesteUsuario {

    Usuario usuario = new Usuario();
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
        usuario = new Usuario();
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
        usuario.setNome("Exemplo");
        assertEquals("Exemplo", usuario.getNome());
    }

    @Test
    @DisplayName("Testa se o CPF está sendo inserido no usuário.")
    void testeCPFUsuario() {
        usuario.setCPF("123");
        assertEquals("123", usuario.getCPF());
    }

    @Test
    @DisplayName("Testa se a idade está sendo inserida no usuário.")
    void testeIdadeUsuario() {
        usuario.setIdade(10);
        assertEquals(10, usuario.getIdade());
    }

    @Test
    @DisplayName("Testa se o telefone está sendo inserido no usuário.")
    void testeTelefoneUsuario() {
        usuario.setTelefone("123");
        assertEquals("123", usuario.getTelefone());
    }

    @Test
    @DisplayName("Testa se o email está sendo inserido no usuário.")
    void testeEmailUsuario() {
        usuario.setEmail("lucas@gmail.com");
        assertEquals("lucas@gmail.com", usuario.getEmail());
    }
}