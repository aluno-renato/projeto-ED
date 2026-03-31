class Pilha<T> {
    private No<T> topo;

    public void push(T dado) {
        No<T> novo = new No<>(dado);
        novo.proximo = topo;
        topo = novo;
    }

    public T pop() {
        if (topo == null) return null;
        T dado = topo.dado;
        topo = topo.proximo;
        return dado;
    }

    public void listar() {
        No<T> atual = topo;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }
}