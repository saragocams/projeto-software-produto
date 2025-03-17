package br.insper.produto.produto.service;

import br.insper.produto.produto.produto.Produto;
import br.insper.produto.produto.produto.ProdutoRepository;
import br.insper.produto.produto.produto.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    void test_findAllProdutos() {
        List<Produto> produtos = Arrays.asList(
                new Produto("1", "Produto 1", 100.0, 100),
                new Produto("2", "Produto 2", 200.0, 200)
        );

        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.getProdutos();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void test_saveProduto() {
        Produto produto = new Produto("1", "Produto Teste", 99.9, 10);

        Mockito.when(produtoRepository.save(Mockito.any())).thenReturn(produto);

        Produto savedProduto = produtoService.saveProduto(produto);
        Assertions.assertEquals("Produto Teste", savedProduto.getNome());
        Assertions.assertEquals(99.9, savedProduto.getPreco());
    }

    @Test
    void test_findProdutoById_Success() {
        Produto produto = new Produto("1", "Produto 1", 100.0, 100);

        Mockito.when(produtoRepository.findById("1")).thenReturn(Optional.of(produto));

        Produto foundProduto = produtoService.getProdutoById("1");
        Assertions.assertEquals("Produto 1", foundProduto.getNome());
    }

}
