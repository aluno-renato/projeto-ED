class PilhaEncadeada<T> {
    private No<T> topo;

    public void push(T dado) {
        No<T> novo = new No<>(dado);
        novo.proximo = topo;
        topo = novo;
    }

    public void listar() {
        No<T> atual = topo;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }
}