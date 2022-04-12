package br.wipro.vanillajava;

public class GerenciarContas {

    public ContaCorrente inserirContaCorrente(int numero, double saldo, String cartaoDeCredito, Cliente cliente) {
        ContaCorrente contaCorrente = new ContaCorrente(numero,saldo,cartaoDeCredito,cliente);
        return contaCorrente;

    }

    public ContaEspecial inserirContaEspecial(int numero, double saldo, String cartaoDeCredito, Cliente cliente, double limite) {
        ContaEspecial contaEspecial = new ContaCorrente(numero,saldo,cartaoDeCredito,cliente, limite);
        return contaEspecial;

    }

}
