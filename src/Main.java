import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DeliverySystem sistema = new DeliverySystem();
        Scanner sc = new Scanner(System.in);

        int op;

        do {
            System.out.println("\n1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Criar Pedido");
            System.out.println("5 - Preparar Próximo Pedido");
            System.out.println("6 - Finalizar Pedido");
            System.out.println("7 - Histórico");
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();

                    sistema.cadastrarCliente(
                        new Cliente(gerarId(), nome, telefone, endereco)
                    );
                    break;

                case 2:
                    sistema.clientes.listar();
                    break;

                case 3:
                    System.out.print("Nome do produto: ");
                    String nomeProd = sc.nextLine();

                    System.out.print("Preço: ");
                    double preco = Double.parseDouble(sc.nextLine());

                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();

                    System.out.print("Estoque: ");
                    int estoque = Integer.parseInt(sc.nextLine());

                    sistema.cadastrarProduto(
                        new Produto(gerarId(), nomeProd, preco, categoria, estoque)
                    );
                    break;

                case 4:
                    System.out.println("Criando pedido...");
                    Cliente cliente = sistema.buscarPrimeiroCliente();

                    Pedido pedido = new Pedido(gerarId(), cliente);
                    sistema.criarPedido(pedido);

                    System.out.println("Pedido criado!");
                    break;

                case 5:
                    Pedido p = sistema.prepararProximoPedido();
                    if (p != null) {
                        System.out.println("Preparando:");
                        p.exibirResumo();
                    } else {
                        System.out.println("Fila vazia.");
                    }
                    break;

                case 6:
                    Pedido finalizado = sistema.prepararProximoPedido();
                    if (finalizado != null) {
                        sistema.finalizarPedido(finalizado);
                        System.out.println("Pedido finalizado!");
                    }
                    break;

                case 7:
                    sistema.exibirHistorico();
                    break;
            }

        } while (op != 0);

        sc.close();
    }

    static int contador = 1;

    public static int gerarId() {
        return contador++;
    }
}