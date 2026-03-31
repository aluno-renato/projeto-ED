class Cliente {
    int idCliente;
    String nome, telefone, endereco;

    public Cliente(int id, String nome, String telefone, String endereco) {
        this.idCliente = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void exibirDados() {
        System.out.println(nome + " - " + telefone);
    }
}