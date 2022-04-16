package br.wipro.vanillajava;

public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, double saldo, String cartaoDeCredito, Cliente cliente) {
        super(numero, saldo, cartaoDeCredito, cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor > this.getSaldo()) throw new IllegalArgumentException("Saldo insuficiente.");
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
        return "ContaCorrente:\n" +
                "\nNúmero: " + numero +
                "\nSaldo: " + saldo +
                "\nCartão de crédito: '" + cartaoDeCredito + '\'' +
                "\nCliente: " + cliente;
    }
}
