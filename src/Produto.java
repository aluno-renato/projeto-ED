public class Produto {
    int id;
    String nome, categoria;
    double preco;
    int estoque;

    public Produto(int id, String nome, double preco, String categoria, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
    }

    public void atualizarEstoque(int qtd) {
        estoque -= qtd;
    }

    public boolean baixarEstoque(int qtd) {
        if (qtd <= estoque) {
            estoque -= qtd;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " | R$" + preco + " | Estoque: " + estoque;
    }
}