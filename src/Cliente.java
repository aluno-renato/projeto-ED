public class Cliente {
    int id;
    String nome, telefone, endereco;

    public Cliente(int id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void atualizarDados(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void cadastrar() {
        System.out.println("Cliente cadastrado: " + nome);
    }

    public void exibirDados() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " | " + telefone + " | " + endereco;
    }
}