package br.wipro.vanillajava;

public class ContaEspecial extends Conta {

	private double limite;

	public ContaEspecial() {

	}

	public ContaEspecial(int numero, double saldo, String cartaoDeCredito, String cpfCliente, double limite) {
		super(numero, saldo, cartaoDeCredito, cpfCliente);
		this.limite = limite;
	}

	@Override
	public void sacar(double valor) {
		if ((this.getSaldo() + this.limite) < valor)
			throw new IllegalArgumentException("Saldo insuficiente");
		this.saldo -= valor;
		System.out.println("Saque realizado");
	}
}
