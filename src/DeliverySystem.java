class DeliverySystem {

    ListaSimples<Cliente> clientes = new ListaSimples<>();
    ListaSimples<Produto> produtos = new ListaSimples<>();

    ListaDupla<Pedido> pedidosAtivos = new ListaDupla<>();
    Fila<Pedido> filaPreparo = new Fila<>();
    Pilha<Pedido> historico = new Pilha<>();

    public Cliente buscarPrimeiroCliente() {
    No<Cliente> atual = clientes.getInicio();
    if (atual != null) return atual.dado;
    return null;
}

    public void cadastrarCliente(Cliente c) {
        clientes.inserir(c);
    }

    public void cadastrarProduto(Produto p) {
        produtos.inserir(p);
    }

    public void criarPedido(Pedido p) {
        pedidosAtivos.inserir(p);
        filaPreparo.enfileirar(p);
    }

    public Pedido prepararProximoPedido() {
        return filaPreparo.desenfileirar();
    }

    public void finalizarPedido(Pedido p) {
        p.status = "FINALIZADO";
        historico.push(p);
    }

    public void exibirHistorico() {
        historico.listar();
    }
}