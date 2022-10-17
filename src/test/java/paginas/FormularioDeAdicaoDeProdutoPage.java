package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeAdicaoDeProdutoPage submeterNomeDoProduto(String produtoNome) {
        navegador.findElement(By.id("produtonome")).click();
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }
    public FormularioDeAdicaoDeProdutoPage submeterValorDoProduto(String produtoValor){
        navegador.findElement(By.id("produtovalor")).click();
        navegador.findElement(By.id("produtovalor")).sendKeys(produtoValor);

        return this;
    }
    public FormularioDeAdicaoDeProdutoPage submeterCorDoProduto(String produtoCor) {
        navegador.findElement(By.id("produtocores")).click();
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCor);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro(){
        navegador.findElement(By.name("action")).click();
        return new ListaDeProdutosPage(navegador);
    }
    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoCorreto(){
        navegador.findElement(By.name("action")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }
}
