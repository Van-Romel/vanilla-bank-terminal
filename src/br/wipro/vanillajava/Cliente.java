package br.wipro.vanillajava;

public class Cliente {

    protected int id;
    protected String nome;
    protected int cpf;

    public Cliente(int id, String nome, int cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
