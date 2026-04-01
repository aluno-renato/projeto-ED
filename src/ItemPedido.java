class ItemPedido {
    Produto produto;
    int quantidade;
    double subtotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    public final void calcularSubtotal() {
        subtotal = produto.preco * quantidade;
    }

    @Override
    public String toString() {
        return produto.nome + " x" + quantidade + " = R$" + subtotal;
    }
}