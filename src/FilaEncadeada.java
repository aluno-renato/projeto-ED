class Fila<T> {
    private No<T> inicio, fim;

    public void enfileirar(T dado) {
        No<T> novo = new No<>(dado);
        if (fim == null) {
            inicio = fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
    }

    public T desenfileirar() {
        if (inicio == null) return null;
        T dado = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        return dado;
    }
}