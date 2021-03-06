package br.wipro.vanillajava;

public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, double saldo, String cartaoDeCredito, String cpfCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cartaoDeCredito = cartaoDeCredito;
        this.cpfCliente = cpfCliente;
    }

    @Override
    public void sacar(double valor) {
        if (valor > this.getSaldo()){
            System.out.println("Saldo insuficiente");
        }
        else {
            this.saldo -= valor;
            System.out.println("Saque realizado");
        }

    }
}
