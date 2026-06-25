import org.example.Model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TesteUser {

    User user;

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes de User: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes de User.");
    }

    @BeforeEach
    void setup() {
        user = new User("Joao", "12345678900", "senha123");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    @Test
    @DisplayName("Testa se o nome está sendo inserido no User.")
    void testeNome() {
        assertEquals("Joao", user.getName());
    }

    @Test
    @DisplayName("Testa se o CPF está sendo inserido no User.")
    void testeCpf() {
        assertEquals("12345678900", user.getCpf());
    }

    @Test
    @DisplayName("Testa se a senha está sendo inserida no User.")
    void testePassword() {
        assertEquals("senha123", user.getPassword());
    }

    @Test
    @DisplayName("Testa o setter de nome no User.")
    void testeSetNome() {
        user.setName("Maria");
        assertEquals("Maria", user.getName());
    }

    @Test
    @DisplayName("Testa o setter de CPF no User.")
    void testeSetCpf() {
        user.setCpf("99888777666");
        assertEquals("99888777666", user.getCpf());
    }

    @Test
    @DisplayName("Testa o setter de senha no User.")
    void testeSetPassword() {
        user.setPassword("novaSenha");
        assertEquals("novaSenha", user.getPassword());
    }

    @Test
    @DisplayName("Testa o getter e setter de tipoDeConta no User.")
    void testeTipoDeConta() {
        assertNull(user.getTipoDeConta());
        user.setTipoDeConta("CORRENTE");
        assertEquals("CORRENTE", user.getTipoDeConta());
    }
}
