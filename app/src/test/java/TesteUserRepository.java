import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TesteUserRepository {

    UserRepository userRepository;

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes de UserRepository: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes de UserRepository.");
    }

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    @Test
    @DisplayName("Testa o cadastro de um novo usuário no repositório.")
    void testeNewUser() {
        User user = new User("Joao", "12345678900", "senha123");
        User resultado = userRepository.newUser(user, "CORRENTE");

        assertEquals(user, resultado);
        assertEquals("CORRENTE", resultado.getTipoDeConta());
        assertEquals("Joao", resultado.getName());
    }

    @Test
    @DisplayName("Testa a busca de um usuário pelo CPF.")
    void testeGetUser() {
        User user = new User("Joao", "12345678900", "senha123");
        userRepository.newUser(user, "12345678900");

        User resultado = userRepository.getUser("12345678900");
        assertNotNull(resultado);
        assertEquals("Joao", resultado.getName());
    }

    @Test
    @DisplayName("Testa a busca por um CPF inexistente.")
    void testeGetUserInexistente() {
        assertNull(userRepository.getUser("00000000000"));
    }

    @Test
    @DisplayName("Testa a atualização de um usuário existente.")
    void testeUpDateUser() {
        User user = new User("Joao", "12345678900", "senha123");
        userRepository.newUser(user, "12345678900");

        User updatedUser = new User("Joao Silva", "12345678900", "novaSenha");
        boolean atualizado = userRepository.upDateUser("12345678900", updatedUser);

        assertTrue(atualizado);
        User resultado = userRepository.getUser("12345678900");
        assertEquals("Joao Silva", resultado.getName());
        assertEquals("novaSenha", resultado.getPassword());
    }

    @Test
    @DisplayName("Testa a atualização de um CPF inexistente.")
    void testeUpDateUserInexistente() {
        User updatedUser = new User("Joao", "00000000000", "senha");
        boolean atualizado = userRepository.upDateUser("00000000000", updatedUser);
        assertFalse(atualizado);
    }

    @Test
    @DisplayName("Testa a deleção de um usuário existente.")
    void testeDeleteUser() {
        User user = new User("Joao", "12345678900", "senha123");
        userRepository.newUser(user, "12345678900");

        boolean deletado = userRepository.deleteUser("12345678900");
        assertTrue(deletado);
        assertNull(userRepository.getUser("12345678900"));
    }

    @Test
    @DisplayName("Testa a deleção de um CPF inexistente.")
    void testeDeleteUserInexistente() {
        boolean deletado = userRepository.deleteUser("00000000000");
        assertFalse(deletado);
    }

    @Test
    @DisplayName("Testa getter e setter de tipoDeConta.")
    void testeTipoDeConta() {
        assertNull(userRepository.getTipoDeConta());
        userRepository.setTipoDeConta("POUPANCA");
        assertEquals("POUPANCA", userRepository.getTipoDeConta());
    }

    @Test
    @DisplayName("Testa se o mapa de usuários é retornado corretamente.")
    void testeGetUsers() {
        assertTrue(userRepository.getUsers().isEmpty());

        User user = new User("Joao", "12345678900", "senha123");
        userRepository.newUser(user, "12345678900");

        assertEquals(1, userRepository.getUsers().size());
        assertTrue(userRepository.getUsers().containsKey("12345678900"));
    }
}
