package br.wipro.vanillajava;

public abstract class Conta {

    protected int numero;
    protected double saldo;
    protected String cartaoDeCredito;
    protected String cpfCliente;

    public Conta() {
    }

    public void dadosConta() {
        System.out.printf("Número da conta: %d%n" +
                        "Saldo da conta: %.2f%n" +
                        "Cartão de crédito: %s%n" +
                        "Cliente: %s%n",
                this.numero, this.saldo, this.cartaoDeCredito, this.cpfCliente);
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public Conta(int numero, double saldo, String cartaoDeCredito, String cpfCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cartaoDeCredito = cartaoDeCredito;
        this.cpfCliente = cpfCliente;
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

    public String getcpfCliente() {
        return cpfCliente;
    }

}
