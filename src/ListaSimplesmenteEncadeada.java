class ListaSimplesmenteEncadeada<T> {
    private No<T> inicio;

    public void inserir(T dado) {
        No<T> novo = new No<>(dado);
        if (inicio == null) {
            inicio = novo;
        } else {
            No<T> atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public void listar() {
        No<T> atual = inicio;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }

    public No<T> getInicio() {
    return inicio;
    }

    public void setInicio(No<T> novoInicio) {
        this.inicio = novoInicio;
    }
}