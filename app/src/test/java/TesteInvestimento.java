import org.example.Entities.Investimento;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class TesteInvestimento {

    Investimento investimento;

    @BeforeAll
    static void mensagemInicial() {
        System.out.println("Inicio dos testes de Investimento: ");
    }

    @AfterAll
    static void mensagemFinal() {
        System.out.println("Fim de todos os testes de Investimento.");
    }

    @BeforeEach
    void setup() {
        investimento = new Investimento("CDB", 1000.0, LocalDate.now().plusDays(30));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teste concluído com sucesso.");
    }

    @Test
    @DisplayName("Testa se o nome do investimento está sendo inserido.")
    void testeNome() {
        assertEquals("CDB", investimento.getNome());
    }

    @Test
    @DisplayName("Testa se o valor do investimento está sendo inserido.")
    void testeValor() {
        assertEquals(1000.0, investimento.getValor());
    }

    @Test
    @DisplayName("Testa se a data de retirada do investimento está sendo inserida.")
    void testeDataDeRetirada() {
        assertEquals(LocalDate.now().plusDays(30), investimento.getDataDeRetirada());
    }

    @Test
    @DisplayName("Testa os setters de Investimento.")
    void testeSetters() {
        investimento.setNome("FII");
        investimento.setValor(500.0);
        investimento.setDataDeRetirada(LocalDate.now().plusDays(60));
        investimento.setRetorno(10.0);

        assertEquals("FII", investimento.getNome());
        assertEquals(500.0, investimento.getValor());
        assertEquals(LocalDate.now().plusDays(60), investimento.getDataDeRetirada());
        assertEquals(10.0, investimento.getRetorno());
    }

    @Test
    @DisplayName("Testa o cálculo de retorno para CDB.")
    void testeCalcularRetornoCDB() {
        investimento.setDataDeRetirada(LocalDate.now().plusDays(30));
        double resultado = investimento.calcularRetorno();
        double esperado = (1000.0 * 0.000332) * 30;
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Testa o cálculo de retorno para Tesouro Direto.")
    void testeCalcularRetornoTesouroDireto() {
        investimento.setNome("Tesouro Direto");
        investimento.setDataDeRetirada(LocalDate.now().plusDays(30));
        double resultado = investimento.calcularRetorno();
        double esperado = (1000.0 * 0.000529) * 30;
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Testa o cálculo de retorno para IBV.")
    void testeCalcularRetornoIBV() {
        investimento.setNome("IBV");
        investimento.setDataDeRetirada(LocalDate.now().plusDays(30));
        double resultado = investimento.calcularRetorno();
        double esperado = (1000.0 * 0.000812) * 30;
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Testa o cálculo de retorno para FII.")
    void testeCalcularRetornoFII() {
        investimento.setNome("FII");
        investimento.setDataDeRetirada(LocalDate.now().plusDays(30));
        double resultado = investimento.calcularRetorno();
        double esperado = (1000.0 * 0.000302) * 30;
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Testa o cálculo de retorno para LCI.")
    void testeCalcularRetornoLCI() {
        investimento.setNome("LCI");
        investimento.setDataDeRetirada(LocalDate.now().plusDays(30));
        double resultado = investimento.calcularRetorno();
        double esperado = (1000.0 * 0.000274) * 30;
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Testa o cálculo de retorno com data no passado.")
    void testeCalcularRetornoDataPassada() {
        investimento.setDataDeRetirada(LocalDate.now().minusDays(10));
        double resultado = investimento.calcularRetorno();
        assertEquals(0.0, resultado, 0.001);
    }
}
