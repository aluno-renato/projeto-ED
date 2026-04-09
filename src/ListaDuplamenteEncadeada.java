class NoDuplo<T> {
    T dado;
    NoDuplo<T> anterior, proximo;

    public NoDuplo(T dado) {
        this.dado = dado;
    }

    public T getDado() {
        return dado;
    }

    public NoDuplo<T> getAnterior() {
        return anterior;
    }

    public NoDuplo<T> getProximo() {
        return proximo;
    }
}

class ListaDuplamenteEncadeada<T> {
    private NoDuplo<T> inicio;

    public void inserir(T dado) {
        NoDuplo<T> novo = new NoDuplo<>(dado);

        if (inicio == null) {
            inicio = novo;
        } else {
            NoDuplo<T> atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
            novo.anterior = atual;
        }
    }

    public NoDuplo<T> getInicio() {
        return inicio;
    }
}