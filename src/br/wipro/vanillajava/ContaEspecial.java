package br.wipro.vanillajava;

public class ContaEspecial extends Conta {
    private double limite;

    @Override
    public void sacar(double valor) {
        if ((this.getSaldo() + this.limite) < valor) throw new IllegalArgumentException("Saldo insuficiente");
        this.saldo -= valor;
    }
}
