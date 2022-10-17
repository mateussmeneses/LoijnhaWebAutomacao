package modulos.produtos;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web no módulo de produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        // Abrir um navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chrome driver 105\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Vou maximizar a tela
        this.navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        // Navegar para a página da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");

    }


    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testeNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        // Fazer Login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .accesarOFormularioDeAdicaoDeNovoProduto()
                .submeterNomeDoProduto("teste")
                .submeterValorDoProduto("0.00")
                .submeterCorDoProduto("preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que R$7000.00")
    public void testNaoEPermitidoRegistrarProdutoMaiorQueSeteMil() {   // Fazer Login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .accesarOFormularioDeAdicaoDeNovoProduto()
                .submeterNomeDoProduto("teste")
                .submeterValorDoProduto("700001")
                .submeterCorDoProduto("preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }


    @Test
    @DisplayName("Posso adicionar produtos que estejam no lmite 0,01")
    public void testAdicionarProdutosDeUmCentavo() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .accesarOFormularioDeAdicaoDeNovoProduto()
                .submeterNomeDoProduto("MacBook Pro")
                .submeterValorDoProduto("30000")
                .submeterCorDoProduto("preto,branco")
                .submeterFormularioDeAdicaoCorreto()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrar produto com valor limite de 7 mil")
        public void registrarProdutoComValorLimiteDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .accesarOFormularioDeAdicaoDeNovoProduto()
                .submeterNomeDoProduto("MacBook Pro")
                .submeterValorDoProduto("700000")
                .submeterCorDoProduto("preto,branco")
                .submeterFormularioDeAdicaoCorreto()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        // Vou fechar o navegador
        navegador.quit();
    }
}


