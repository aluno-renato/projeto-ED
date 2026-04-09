import java.util.Scanner;

public class Main {

    static int id = 1;

    public static void main(String[] args) {

        DeliverySystem sistema = new DeliverySystem();

        try (Scanner sc = new Scanner(System.in)) {
            int op;

            do {
                System.out.println("\n1 - Cadastrar Cliente");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Cadastrar Produto");
                System.out.println("4 - Listar Produtos");
                System.out.println("5 - Criar Pedido");
                System.out.println("6 - Adicionar Item ao Pedido");
                System.out.println("7 - Enviar Pedido para Fila");
                System.out.println("8 - Ver Fila");
                System.out.println("9 - Preparar Pedido");
                System.out.println("10 - Finalizar Pedido");
                System.out.println("11 - Histórico");
                System.out.println("12 - Navegar Pedidos Ativos");
                System.out.println("0 - Sair");

                op = Integer.parseInt(sc.nextLine());

                switch (op) {

                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Telefone: ");
                        String tel = sc.nextLine();

                        System.out.print("Endereço: ");
                        String end = sc.nextLine();

                        Cliente c = new Cliente(id++, nome, tel, end);
                        c.cadastrar();
                        sistema.cadastrarCliente(c);
                    }

                    case 2 -> {
                        System.out.println("\n========== CLIENTES ==========");
                        sistema.listarClientes();
                    }

                    case 3 -> {
                        System.out.print("Nome produto: ");
                        String np = sc.nextLine();

                        System.out.print("Preço: ");
                        double pr = Double.parseDouble(sc.nextLine());

                        System.out.print("Categoria: ");
                        String cat = sc.nextLine();

                        System.out.print("Estoque: ");
                        int est = Integer.parseInt(sc.nextLine());

                        Produto p = new Produto(id++, np, pr, cat, est);
                        sistema.cadastrarProduto(p);
                    }

                    case 4 -> {
                        System.out.println("\n========== PRODUTOS ==========");
                        sistema.listarProdutos();
                    }

                    case 5 -> {
                        sistema.listarClientes();
                        System.out.print("ID Cliente: ");
                        int idc = Integer.parseInt(sc.nextLine());

                        Cliente c = sistema.buscarCliente(idc);

                        if (c != null) {
                            Pedido pedido = new Pedido(id++, c);
                            sistema.criarPedido(pedido);
                            System.out.println("Pedido criado (em edição)!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                    }

                    case 6 -> {
                        System.out.print("ID do Pedido: ");
                        int idPedido = Integer.parseInt(sc.nextLine());

                        Pedido pedido = sistema.buscarPedidoAtivo(idPedido);

                        if (pedido == null) {
                            System.out.println("Pedido não encontrado!");
                            break;
                        }

                        sistema.listarProdutos();
                        System.out.print("ID Produto: ");
                        int idp = Integer.parseInt(sc.nextLine());

                        Produto prod = sistema.buscarProduto(idp);

                        if (prod == null) {
                            System.out.println("Produto não encontrado!");
                            break;
                        }

                        System.out.print("Quantidade: ");
                        int qtd = Integer.parseInt(sc.nextLine());

                        pedido.adicionarItem(new ItemPedido(prod, qtd));
                        System.out.println("Item adicionado!");
                    }

                    case 7 -> {
                        System.out.print("ID do Pedido: ");
                        int idPedido = Integer.parseInt(sc.nextLine());

                        Pedido pedido = sistema.buscarPedidoAtivo(idPedido);

                        if (pedido != null) {
                            sistema.enfileirarPedido(pedido);
                            System.out.println("Pedido enviado para fila!");
                        } else {
                            System.out.println("Pedido não encontrado!");
                        }
                    }

                    case 8 -> {
                        System.out.println("\n========== FILA ==========");
                        sistema.mostrarFila();
                    }

                    case 9 -> {
                        Pedido p = sistema.prepararProximoPedido();
                        if (p != null) {
                            System.out.println("Preparando:");
                            p.exibirResumo();
                        } else {
                            System.out.println("Fila vazia!");
                        }
                    }

                    case 10 -> {
                        sistema.finalizarPedido();
                        System.out.println("Pedido finalizado!");
                    }

                    case 11 -> {
                        System.out.println("\n========== HISTÓRICO ==========");
                        sistema.exibirHistorico();
                    }

                    case 12 -> {
                        System.out.println("\n========== PEDIDOS ATIVOS ==========");
                        sistema.navegarPedidosAtivos();
                    }
                }

            } while (op != 0);
        }
    }
}