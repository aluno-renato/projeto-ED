class Produto {
    int idProduto;
    String nome, categoria;
    double preco;
    int estoque;

    public Produto(int id, String nome, double preco, String categoria, int estoque) {
        this.idProduto = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
    }

    public void atualizarEstoque(int qtd) {
        estoque -= qtd;
    }
}