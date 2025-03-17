package br.insper.produto.repository;

import br.insper.produto.produto.Produto;
import br.insper.produto.produto.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProdutoRepositoryTests {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void test_SaveAndFindProduto() {
        Produto produto = new Produto(null, "Produto Teste", 50.0, 10);
        produtoRepository.save(produto);

        List<Produto> produtos = produtoRepository.findAll();
        Assertions.assertEquals(1, produtos.size());
        Assertions.assertEquals("Produto Teste", produtos.get(0).getNome());
    }
}
