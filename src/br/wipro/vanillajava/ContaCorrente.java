package br.wipro.vanillajava;

public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, double saldo, String cartaoDeCredito, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cartaoDeCredito = cartaoDeCredito;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        if (valor > this.getSaldo()) throw new IllegalArgumentException("Saldo insuficiente");
        this.saldo -= valor;
    }
}
