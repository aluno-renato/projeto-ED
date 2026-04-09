public class Pedido {
    int id;
    Cliente cliente;
    String status;
    double total;
    String enderecoEntrega;

    ListaSimplesmenteEncadeada<ItemPedido> itens = new ListaSimplesmenteEncadeada<>();

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.enderecoEntrega = cliente.endereco;
        this.status = "CRIADO";
    }

    public void adicionarItem(ItemPedido item) {

    if (item.produto.estoque < item.quantidade) {
        System.out.println("Estoque insuficiente!");
        return;
    }

    item.produto.atualizarEstoque(item.quantidade);
    itens.inserir(item);
    calcularTotal();
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

    public void finalizarPedido() {
        this.status = "FINALIZADO";
    }

    public void exibirResumo() {
        System.out.println("\nPedido #" + id + " | Cliente: " + cliente.nome);
        itens.listar();
        System.out.println("Total: R$" + total + " | Status: " + status);
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - " + cliente.nome + " - R$" + total;
    }
}