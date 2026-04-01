class DeliverySystem {

    ListaSimplesmenteEncadeada<Cliente> clientes = new ListaSimplesmenteEncadeada<>();
    ListaSimplesmenteEncadeada<Produto> produtos = new ListaSimplesmenteEncadeada<>();

    ListaDuplamenteEncadeada<Pedido> pedidosAtivos = new ListaDuplamenteEncadeada<>();
    FilaEncadeada<Pedido> fila = new FilaEncadeada<>();
    PilhaEncadeada<Pedido> historico = new PilhaEncadeada<>();

    public void cadastrarCliente(Cliente c) {
        clientes.inserir(c);
    }

    public void listarClientes() {
    if (clientes.getInicio() == null) {
        System.out.println("Nenhum cliente cadastrado.");
    } else {
        clientes.listar();
    }
}

    public void cadastrarProduto(Produto p) {
        produtos.inserir(p);
    }

    public void listarProdutos() {
        produtos.listar();
    }

    public Cliente buscarCliente(int id) {
        No<Cliente> atual = clientes.getInicio();
        while (atual != null) {
            if (atual.dado.id == id) return atual.dado;
            atual = atual.proximo;
        }
        return null;
    }

    public Produto buscarProduto(int id) {
        No<Produto> atual = produtos.getInicio();
        while (atual != null) {
            if (atual.dado.id == id) return atual.dado;
            atual = atual.proximo;
        }
        return null;
    }

    public void criarPedido(Pedido p) {
        pedidosAtivos.inserir(p);
        fila.enfileirar(p);
    }

    public Pedido prepararProximoPedido() {
        return fila.desenfileirar();
    }

    public void finalizarPedido(Pedido p) {
        p.status = "FINALIZADO";
        historico.push(p);
    }

    public void mostrarFila() {
        fila.listar();
    }

    public void mostrarHistorico() {
        historico.listar();
    }
}