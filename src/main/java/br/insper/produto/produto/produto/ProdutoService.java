package br.insper.produto.produto.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }


    public Produto getProdutoById(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public Produto diminuirEstoque(String id, int quantidade) {
        Produto produto = getProdutoById(id);
        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente!");
        }
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }
}
