package br.wipro.vanillajava;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial() {

	}

	public ContaEspecial(int numero, double salario, String cartaoDeCredito, Cliente cliente, double limite) {
		super(numero, salario, cartaoDeCredito, cliente);
		this.limite = limite;
	}

	@Override
	public void sacar(double valor) {
		if ((this.getSaldo() + this.limite) < valor)
			throw new IllegalArgumentException("Saldo insuficiente");
		this.saldo -= valor;
	}
}
