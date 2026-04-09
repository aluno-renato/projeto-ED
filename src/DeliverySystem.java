class DeliverySystem {

    ListaSimplesmenteEncadeada<Cliente> clientes = new ListaSimplesmenteEncadeada<>();
    ListaSimplesmenteEncadeada<Produto> produtos = new ListaSimplesmenteEncadeada<>();
    ListaSimplesmenteEncadeada<Pedido> pedidosPreparados = new ListaSimplesmenteEncadeada<>();

    ListaDuplamenteEncadeada<Pedido> pedidosAtivos = new ListaDuplamenteEncadeada<>();
    FilaEncadeada<Cliente> filaClientes = new FilaEncadeada<>();
    FilaEncadeada<Produto> filaProdutos = new FilaEncadeada<>();
    FilaEncadeada<Pedido> fila = new FilaEncadeada<>();
    PilhaEncadeada<Pedido> historico = new PilhaEncadeada<>();

    public void cadastrarCliente(Cliente c) {
    clientes.inserir(c);
    filaClientes.enfileirar(c);
}

    public void listarClientes() {
    System.out.println("\n========== LISTA DE CLIENTES ==========");

    if (clientes.getInicio() == null) {
        System.out.println("Nenhum cliente cadastrado.");
        return;
    }

    clientes.listar();
}

    public void cadastrarProduto(Produto p) {
    produtos.inserir(p);
    filaProdutos.enfileirar(p);
    }

    public void listarProdutos() {
    System.out.println("\n========== LISTA DE PRODUTOS ==========");

    if (produtos.getInicio() == null) {
        System.out.println("Nenhum produto cadastrado.");
        return;
    }

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
        NoDuplo<Pedido> atual = pedidosAtivos.getInicio();

        while (atual != null) {
            if (atual.dado.id == p.id) {
                System.out.println("Pedido já existe!");
                return;
            }
            atual = atual.proximo;
        }
        pedidosAtivos.inserir(p);
        fila.enfileirar(p);
    }

    public Pedido prepararProximoPedido() {

        Pedido p = fila.desenfileirar();

        if (p != null) {
            p.status = "PREPARADO";
            pedidosPreparados.inserir(p);
            System.out.println("Pedido preparado: " + p.id);
        } else {
            System.out.println("Fila vazia, nada para preparar.");
        }

        return p;
    }

    public void listarPedidosPreparados() {
        System.out.println("\n========== PEDIDOS PREPARADOS ==========");

        if (pedidosPreparados.getInicio() == null) {
            System.out.println("Nenhum pedido preparado.");
            return;
        }
        pedidosPreparados.listar();
    }

    public Pedido buscarPedidoPreparado(int id) {
        No<Pedido> atual = pedidosPreparados.getInicio();

        while (atual != null) {
            if (atual.dado.id == id) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void removerPedidoPreparado(int id) {
        No<Pedido> atual = pedidosPreparados.getInicio();
        No<Pedido> anterior = null;

        while (atual != null) {

            if (atual.dado.id == id) {
                if (anterior == null) {
                    pedidosPreparados = new ListaSimplesmenteEncadeada<>();
                    pedidosPreparados.inserir(atual.proximo != null ? atual.proximo.dado : null);
                } else {
                    anterior.proximo = atual.proximo;
                }
                return;
            }
            anterior = atual;
            atual = atual.proximo;
        }
    }

    public Pedido buscarPedidoPorId(int id) {
        NoDuplo<Pedido> atual = pedidosAtivos.getInicio();

        while (atual != null) {
            if (atual.dado.id == id) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }
    
    public void listarPedidosAtivos() {
    System.out.println("\n========== PEDIDOS ATIVOS ==========");

    NoDuplo<Pedido> atual = pedidosAtivos.getInicio();

    if (atual == null) {
        System.out.println("Nenhum pedido ativo.");
        return;
    }

    while (atual != null) {
        System.out.println(atual.dado);
        atual = atual.proximo;
        }
    }

    public void finalizarPedido(int id) {
        Pedido p = buscarPedidoPreparado(id);

        if (p == null) {
          System.out.println("Pedido não encontrado nos preparados.");
          return;
        }

        p.status = "FINALIZADO";
        historico.push(p);

        removerPedidoPreparado(id);

        System.out.println("Pedido finalizado!");
    }

    public void mostrarFilaClientes() {
        if (filaClientes.estaVazia()) {
            System.out.println("Fila de clientes vazia.");
        } else {
            filaClientes.listar();
        }
    }

    public void mostrarFilaProdutos() {
        if (filaProdutos.estaVazia()) {
        System.out.println("Fila de produtos vazia.");
        } else {
            filaProdutos.listar();
        }
    }

    public void mostrarHistorico() {
        System.out.println("\n========== HISTÓRICO DE PEDIDOS ==========");

        if (historico.estaVazia()) {
            System.out.println("Nenhum pedido finalizado.");
            return;
        }
        historico.listar();
    }

    public boolean historicoVazio() {
        try {
            historico.listar();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}