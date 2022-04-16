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
        }
        ;
        this.saldo -= valor;
    }

    public DataBase transferir(DataBase dataBase, int numeroContaDestino, double valor) {
        var contaCorrenteDestino = dataBase.contasCorrente.stream().findFirst()
                .filter(conta -> conta.getNumero() == numeroContaDestino).orElse(null);
        var contaEspecialDestino = dataBase.contasEspeciais.stream().findFirst()
                .filter(conta -> conta.getNumero() == numeroContaDestino).orElse(null);
        if (contaCorrenteDestino != null) {
            try {
                super.transferir(contaCorrenteDestino, valor);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return dataBase;
        } else if (contaEspecialDestino != null) {
            try {
                super.transferir(contaEspecialDestino, valor);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return dataBase;
        } else {
            System.out.println("Conta de destino não encontrada.");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Conta Especial: \n" +
                "\nNúmero: " + numero +
                "\nSaldo: " + saldo +
                "\nCartão de crédito: '" + cartaoDeCredito + '\'' +
                "\nCliente: " + cliente +
                "\nLimite: " + limite;
    }
}
