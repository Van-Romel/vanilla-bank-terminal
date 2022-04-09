package br.wipro.vanillajava;

public abstract class Conta {

    protected int numero;
    protected double saldo;
    protected CartaoDeCredito cartaoDeCredito;

    public Conta() {
    }

    public void dadosConta() {
        System.out.printf("Número da conta: %d%n" +
                        "Saldo da conta: %.2f%n" +
                        "Cartão de crédito: %s%n",
                this.numero, this.saldo, this.cartaoDeCredito.toString());
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public CartaoDeCredito getCartaoDeCredito() {
        return cartaoDeCredito;
    }
}
