package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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

    public DataBase removeConta(DataBase dataBase, ContaCorrente contaCorrente, ContaEspecial contaEspecial) {

        if (contaCorrente != null) {
            dataBase.contasCorrente.remove(contaCorrente);
        } else if (contaEspecial != null) {
            dataBase.contasEspeciais.remove(contaEspecial);
        } else {
            System.out.println("Conta não encontrada.");
        }
        return dataBase;
    }

    public String consultarDadosConta(ContaCorrente contaCorrente, ContaEspecial contaEspecial) {
        if (contaCorrente != null) {
            return contaCorrente.toString();
        } else if (contaEspecial != null) {
            return contaEspecial.toString();
        } else return "Conta não encontrada.";
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

    public Optional<Cliente> confirmaLogin(ArrayList<Cliente> clientes, String cpf, Scanner scanner) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Digite o seu PIN de acesso, por favor.");
            String pin1 = scanner.next();

            for (Cliente cliente : clientes) {
                if (cliente.cpf.equals(cpf) && cliente.pin.equals(pin1)) {
                    System.out.println("Bem-vindo, " + cliente.nome + "!");
                    return Optional.of(cliente);
                } else
                    System.out.println("O PIN inserido é inválido.\n\n\n\n Você tem mais " + (2 - i) + " tentativas.");
            }
            if (i == 2) return Optional.empty();
        }
        return Optional.empty();
    }
}
