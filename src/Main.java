import java.util.Scanner;

public class Main {

    static int idCliente = 1;
    static int idProduto = 1;
    static int idPedido = 1;

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
                System.out.println("7 - Ver Fila De CLientes");
                System.out.println("8 - Ver Fila De Produtos");
                System.out.println("9 - Editar Pedido");
                System.out.println("10 - Finalizar Pedido");
                System.out.println("11 - Histórico");
                System.out.println("0 - Sair");

                op = Integer.parseInt(sc.nextLine());

                switch (op) {
                    case 1: {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Telefone: ");
                        String tel = sc.nextLine();

                        System.out.print("Endereço: ");
                        String end = sc.nextLine();

                        sistema.cadastrarCliente(
                        new Cliente(idCliente++, nome, tel, end)
                        );
                        break;
                    }
                    case 2: {
                        sistema.listarClientes();
                        break;
                    }
                    case 3: {
                        System.out.print("Nome produto: ");
                        String np = sc.nextLine();

                        System.out.print("Preço: ");
                        double pr = Double.parseDouble(sc.nextLine());

                        System.out.print("Categoria: ");
                        String cat = sc.nextLine();

                        System.out.print("Estoque: ");
                        int est = Integer.parseInt(sc.nextLine());

                        sistema.cadastrarProduto(
                            new Produto(idProduto++, np, pr, cat, est)
                            );
                        break;
                    }
                    case 4: {
                        sistema.listarProdutos();
                        break;
                    }
                    case 5: {
                        sistema.listarClientes();

                        System.out.print("ID Cliente: ");
                        int idc = Integer.parseInt(sc.nextLine());

                        Cliente c = sistema.buscarCliente(idc);

                        if (c != null) {
                            sistema.criarPedido(new Pedido(idPedido++, c));
                            System.out.println("Pedido criado!");
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                        break;
                    }
                    case 6: {
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
                        System.out.println("Item adicionado!");
                        
                        System.out.println("Item adicionado ao pedido!");
                        break;
                    }
                    
                    case 7: {
                        System.out.println("\n=== FILA DE CLIENTES ===");
                        sistema.mostrarFilaClientes();
                        break;
                    }

                    case 8: {
                        System.out.println("\n=== FILA DE PRODUTOS ===");
                        sistema.mostrarFilaProdutos();
                        break;
                    }

                    case 9: {
                        sistema.listarPedidosAtivos();

                        System.out.print("ID do Pedido: ");
                        int idPed = Integer.parseInt(sc.nextLine());

                        Pedido ped = sistema.buscarPedidoPorId(idPed);

                        if (ped == null) {
                            System.out.println("Pedido não encontrado.");
                            break;
                        }

                        if (ped.status.equals("FINALIZADO")) {
                            System.out.println("Pedido já finalizado!");
                            break;
                        }
                        int opEdit;
                        do {
                            System.out.println("\n=== EDITAR PEDIDO #" + ped.id + " ===");
                            System.out.println("1 Adicionar item");
                            System.out.println("2 Remover item");
                            System.out.println("3 Ver resumo");
                            System.out.println("0 Voltar");

                            opEdit = Integer.parseInt(sc.nextLine());

                            switch (opEdit) {

                                case 1: {
                                    sistema.listarProdutos();

                                    System.out.print("ID Produto: ");
                                    int idProd = Integer.parseInt(sc.nextLine());

                                    Produto prod = sistema.buscarProduto(idProd);

                                    if (prod == null) {
                                        System.out.println("Produto não encontrado.");
                                        break;
                                    }

                                    System.out.print("Quantidade: ");
                                    int qtd = Integer.parseInt(sc.nextLine());

                                    ped.adicionarItem(new ItemPedido(prod, qtd));
                                    System.out.println("Item adicionado!");
                                    break;
                                }
                                case 2:{
                                    System.out.print("ID Produto para remover: ");
                                    int idRem = Integer.parseInt(sc.nextLine());

                                    ped.removerItem(idRem);
                                    break;
                                }

                                case 3: {
                                    ped.exibirResumo();
                                    break;
                                }
                            }
                        } while (opEdit != 0);
                        break;
                    }
                    case 10: {
                        sistema.listarPedidosPreparados();

                        System.out.print("ID do pedido para finalizar: ");
                        int idFinal = Integer.parseInt(sc.nextLine());

                        sistema.finalizarPedido(idFinal);

                    break;
                    }
                    case 11:
                        sistema.mostrarHistorico();
                        break;
                }
            } while (op != 0);
        }
    }
}