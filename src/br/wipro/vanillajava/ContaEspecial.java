package br.wipro.vanillajava;

import java.util.List;

public class ContaEspecial extends Conta {

	private double limite;

	public ContaEspecial() {

	}

	public ContaEspecial(int numero, double salario, String cartaoDeCredito, Cliente cliente, double limite) {
		super(numero, salario, cartaoDeCredito, cliente);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	@Override
	public void sacar(double valor) {
		if ((this.getSaldo() + this.limite) < valor)
			throw new IllegalArgumentException("Saldo e limite insuficientes.");
		if (this.saldo - valor < 0) {
			this.limite = this.limite - (valor - this.saldo);
			this.saldo = 0;
			return;
		};
		this.saldo -= valor;
	}

	@Override
	public void transferir(List<ContaCorrente> contasCorrente, double valor) {
		ContaCorrente contaCorrenteDestino = contasCorrente.stream().findFirst()
				.filter(conta -> conta.getNumero() == numeroContaDestino).orElse(null);
		if (contaCorrenteDestino != null) {
			contaCorrenteSelecionada.transferir(contaCorrenteDestino, valor);
		} else {
			ContaEspecial contaCorrenteDestinoEspecial = contasEspecial.stream().findFirst()
		}
		this.sacar(valor);
		contaDestino.depositar(valor);
	}
}
