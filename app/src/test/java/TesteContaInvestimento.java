import org.example.Entities.ContaInvestimento;
import org.example.Entities.Investimento;
import org.example.Repositories.TipoConta;
import org.example.Repositories.Usuario;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class TesteContaInvestimento {

    Usuario usuario = new Usuario("login", "123", 30, "33468613", "email@gmail.com");
    ContaInvestimento contaInvestimento = new ContaInvestimento("login", usuario, "123");

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes de ContaInvestimento: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes de ContaInvestimento.");
    }

    @BeforeEach
    void setup() {
        Usuario usuario = new Usuario("login", "123", 30, "33468613", "email@gmail.com");
        contaInvestimento = new ContaInvestimento("login", usuario, "123");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    @Test
    @DisplayName("Testa se o número da conta investimento está sendo inserido.")
    void testeNumeroConta() {
        assertEquals("123", contaInvestimento.getNumeroConta());
    }

    @Test
    @DisplayName("Testa se o saldo está sendo inserido na conta investimento.")
    void testeSaldo() {
        contaInvestimento.setSaldo(10);
        assertEquals(10, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo depositar na conta investimento.")
    void testeDeposito() {
        contaInvestimento.depositar(10);
        assertEquals(10, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo sacar na conta investimento.")
    void testeSaque() {
        contaInvestimento.depositar(20);
        contaInvestimento.sacar(5);
        assertEquals(15, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se estamos conseguindo realizar um investimento.")
    void testeInvestir() {
        contaInvestimento.depositar(100);
        Investimento investimento = new Investimento("CDB", 50, LocalDate.now().plusDays(30));
        contaInvestimento.investir(investimento);
        assertEquals(50, contaInvestimento.getSaldo());
    }

    @Test
    @DisplayName("Testa se o tipo da conta é INVESTIMENTO.")
    void testeTipoConta() {
        assertEquals(TipoConta.INVESTIMENTO, contaInvestimento.getTipoConta());
    }
}
