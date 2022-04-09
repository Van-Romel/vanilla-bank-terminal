package br.wipro.vanillajava;

public class ContaCorrente extends Conta {
    @Override
    public void sacar(double valor) {
        if (valor > this.getSaldo()) throw new IllegalArgumentException("Saldo insuficiente");
        this.saldo -= valor;
    }
}
