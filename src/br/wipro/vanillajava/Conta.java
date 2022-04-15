package br.wipro.vanillajava;

public abstract class Conta {

    protected int numero;
    protected double saldo;
    protected String cartaoDeCredito;
    // TODO - quando criar um cliente, tem que importar ele aqui.
    protected Cliente cliente;

    public Conta() {
    }

    public void dadosConta() {
        System.out.printf("Número da conta: %d%n" +
                        "Saldo da conta: %.2f%n" +
                        "Cartão de crédito: %s%n" +
                        "Cliente: %s%n",
                this.numero, this.saldo, this.cartaoDeCredito, this.cliente.getNome());
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public void transferir(Conta contaDestino, double valor) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public Conta(int numero, double saldo, String cartaoDeCredito, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cartaoDeCredito = cartaoDeCredito;
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
