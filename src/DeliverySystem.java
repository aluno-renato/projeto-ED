public class DeliverySystem {

    ListaSimplesmenteEncadeada<Cliente> clientes = new ListaSimplesmenteEncadeada<>();
    ListaSimplesmenteEncadeada<Produto> produtos = new ListaSimplesmenteEncadeada<>();

    ListaDuplamenteEncadeada<Pedido> pedidosAtivos = new ListaDuplamenteEncadeada<>();
    FilaEncadeada<Pedido> fila = new FilaEncadeada<>();
    PilhaEncadeada<Pedido> historico = new PilhaEncadeada<>();

    Pedido pedidoEmPreparo = null;

    public void cadastrarCliente(Cliente c) {
        clientes.inserir(c);
    }

    public void listarClientes() {
        clientes.listar();
    }

    public void cadastrarProduto(Produto p) {
        produtos.inserir(p);
    }

    public void listarProdutos() {
        produtos.listar();
    }

    public void criarPedido(Pedido p) {
        pedidosAtivos.inserir(p); // ✅ não entra na fila aqui
    }

    public void enfileirarPedido(Pedido p) {
        fila.enfileirar(p);
    }

    public Pedido prepararProximoPedido() {
        pedidoEmPreparo = fila.desenfileirar();
        return pedidoEmPreparo;
    }

    public void finalizarPedido() {
    if (pedidoEmPreparo != null) {
        pedidoEmPreparo.finalizarPedido();

        historico.push(pedidoEmPreparo);

        removerPedidoAtivo(pedidoEmPreparo.id);

        pedidoEmPreparo = null;

    } else {
        System.out.println("Nenhum pedido em preparo!");
    }
}

    public void removerPedidoAtivo(int id) {
    NoDuplo<Pedido> atual = pedidosAtivos.getInicio();

    while (atual != null) {
        if (atual.getDado().id == id) {

            if (atual.getAnterior() != null) {
                atual.getAnterior().proximo = atual.getProximo();
            } else {
                pedidosAtivos = new ListaDuplamenteEncadeada<>();
                if (atual.getProximo() != null)
                    pedidosAtivos.inserir(atual.getProximo().getDado());
            }

            if (atual.getProximo() != null) {
                atual.getProximo().anterior = atual.getAnterior();
            }

            return;
        }
        atual = atual.getProximo();
    }
}

    public void mostrarFila() {
        fila.listar();
    }

    public void exibirHistorico() {
        historico.listar();
    }

    public void navegarPedidosAtivos() {
        NoDuplo<Pedido> atual = pedidosAtivos.getInicio();
        while (atual != null) {
            atual.getDado().exibirResumo();
            atual = atual.getProximo();
        }
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

    public Pedido buscarPedidoAtivo(int id) {
        NoDuplo<Pedido> atual = pedidosAtivos.getInicio();
        while (atual != null) {
            if (atual.getDado().id == id) return atual.getDado();
            atual = atual.getProximo();
        }
        return null;
    }
}