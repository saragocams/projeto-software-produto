package br.insper.produto.produto.controller;
import br.insper.produto.produto.produto.Produto;
import br.insper.produto.produto.produto.ProdutoController;
import br.insper.produto.produto.produto.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTests {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void test_GetProdutos() throws Exception {
        List<Produto> produtos = Arrays.asList(
                new Produto("1", "Produto 1", 100.0, 100),
                new Produto("2", "Produto 2", 200.0, 200)
        );

        Mockito.when(produtoService.getProdutos()).thenReturn(produtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/produto"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(produtos)));
    }

    @Test
    void test_PostProduto() throws Exception {
        Produto produto = new Produto("1", "Novo Produto", 150.0, 150);

        Mockito.when(produtoService.saveProduto(Mockito.any())).thenReturn(produto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(produto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(produto)));
    }
}
