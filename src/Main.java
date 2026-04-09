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
                System.out.println("7 - Ver Fila");
                System.out.println("8 - Preparar Pedido");
                System.out.println("9 - Finalizar Pedido");
                System.out.println("10 - Histórico");
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

                        sistema.cadastrarCliente(new Cliente(id++, nome, tel, end));
                    }
                    case 2 -> {
                        System.out.println("\n========== LISTA DE CLIENTES ==========");
                        sistema.listarClientes();
                    break;
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

                        sistema.cadastrarProduto(new Produto(id++, np, pr, cat, est));
                    }
                    case 4 -> {
                        System.out.println("\n========== LISTA DE PRODUTOS ==========");
                        sistema.listarProdutos();
                        break;
                    }
                    case 5 -> {
                        sistema.listarClientes();
                        System.out.print("ID Cliente: ");
                        int idc = Integer.parseInt(sc.nextLine());

                        Cliente c = sistema.buscarCliente(idc);
                        if (c != null) {
                            sistema.criarPedido(new Pedido(id++, c));
                            System.out.println("Pedido criado!");
                        }
                    }
                    case 6 -> {
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
                        
                        Pedido pedido = sistema.prepararProximoPedido();
                        
                        if (pedido == null) {
                            System.out.println("Nenhum pedido disponível!");
                            break;
                        }
                        pedido.adicionarItem(new ItemPedido(prod, qtd));
                        sistema.criarPedido(pedido);
                        
                        System.out.println("Item adicionado ao pedido!");
                        break;
                    }
                    
                    case 7 -> sistema.mostrarFila();
                    case 8 -> {
                        Pedido p = sistema.prepararProximoPedido();
                        if (p != null) p.exibirResumo();
                    }
                    case 9 -> {
                        Pedido f = sistema.prepararProximoPedido();
                        if (f != null) {
                            sistema.finalizarPedido(f);
                            System.out.println("Finalizado!");
                        }
                    }
                    case 10 -> sistema.mostrarHistorico();
                }

            } while (op != 0);
        }
    }
}