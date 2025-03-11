package br.insper.produto.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto saveProduto(@RequestBody Produto produto) {
        return produtoService.saveProduto(produto);
    }


    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.getProdutos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable String id) {
        Produto produto = produtoService.getProdutoById(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}/diminuir")
    public ResponseEntity<Produto> diminuirEstoque(@PathVariable String id, @RequestParam int quantidade) {
        Produto produto = produtoService.diminuirEstoque(id, quantidade);
        return ResponseEntity.ok(produto);
    }
}
