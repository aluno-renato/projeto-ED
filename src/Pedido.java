class Pedido {
    int id;
    Cliente cliente;
    String status;
    double total;

    ListaSimplesmenteEncadeada<ItemPedido> itens = new ListaSimplesmenteEncadeada<>();

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.status = "CRIADO";
    }

    public void adicionarItem(ItemPedido item) {
        if (item.produto.baixarEstoque(item.quantidade)) {

            itens.inserir(item);
            calcularTotal();

            System.out.println("Item adicionado e estoque atualizado!");

        } else {
            System.out.println("Estoque insuficiente para: " + item.produto.nome);
        }
    }

    public void calcularTotal() {
        double soma = 0;
        No<ItemPedido> atual = itens.getInicio();

        while (atual != null) {
            soma += atual.dado.subtotal;
            atual = atual.proximo;
        }

        total = soma;
    }

    public void exibirResumo() {
        System.out.println("\nPedido #" + id + " | Cliente: " + cliente.nome);
        itens.listar();
        System.out.println("Total: R$" + total + " | Status: " + status);
    }

    public void removerItem(int idProduto) {
        No<ItemPedido> atual = itens.getInicio();
        No<ItemPedido> anterior = null;

        while (atual != null) {
            if (atual.dado.produto.id == idProduto) {
                if (anterior == null) {
                    itens.setInicio(atual.proximo);
                } else {
                    anterior.proximo = atual.proximo;
                }
                calcularTotal();
                System.out.println("Item removido!");
                return;
            }

            anterior = atual;
            atual = atual.proximo;
        }

        System.out.println("Produto não encontrado no pedido.");
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - " + cliente.nome + " - R$" + total;
    }
}