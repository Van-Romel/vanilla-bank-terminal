package br.wipro.vanillajava;

import java.util.List;

public abstract class Conta {

    private int numero;
    private double saldo;
    private CartaoDeCredito cartaoDeCredito;

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
        if (valor > this.saldo) throw new IllegalArgumentException("Saldo insuficiente");
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
