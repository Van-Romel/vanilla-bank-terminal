package br.wipro.vanillajava;

import java.util.ArrayList;

public class GerenciaContas {

    ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
    ArrayList<ContaEspecial> contasEspeciais = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrarCliente(String nome, String cpf, String pin) {
        Cliente cliente = new Cliente(nome, cpf, pin);
        clientes.add(cliente);
        return cliente;
    }

    public ContaCorrente inserirContaCorrente(int numero, double saldo, String cartaoDeCredito, Cliente cliente) {
        ContaCorrente contaCorrente = new ContaCorrente(numero, saldo, cartaoDeCredito, cliente);
        contasCorrente.add(contaCorrente);
        return contaCorrente;
    }

    public ContaEspecial inserirContaEspecial(int numero, double saldo, String cartaoDeCredito, Cliente cliente, double limite) {
        ContaEspecial contaEspecial = new ContaEspecial(numero, saldo, cartaoDeCredito, cliente, limite);
        contasEspeciais.add(contaEspecial);
        return contaEspecial;
    }

    public void removeContaCorrente(ContaCorrente contaCorrente) {
        contasCorrente.remove(contaCorrente);
    }

    public ArrayList<ContaCorrente> getContasCorrente() {
        return contasCorrente;
    }

    public ArrayList<ContaEspecial> getContasEspeciais() {
        return contasEspeciais;
    }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

}
