class Pedido {
    int idPedido;
    Cliente cliente;
    String status;
    double valorTotal;
    String enderecoEntrega;

    ListaSimples<ItemPedido> itens = new ListaSimples<>();

    public Pedido(int id, Cliente cliente) {
        this.idPedido = id;
        this.cliente = cliente;
        this.status = "CRIADO";
    }

    public void adicionarItem(ItemPedido item) {
        itens.inserir(item);
        calcularTotal();
    }

    public void calcularTotal() {
        double total = 0;
        No<ItemPedido> atual = itens.getInicio();
        while (atual != null) {
            total += atual.dado.subtotal;
            atual = atual.proximo;
        }
        valorTotal = total;
    }

    public void exibirResumo() {
        System.out.println("Pedido #" + idPedido + " Total: " + valorTotal);
    }
}