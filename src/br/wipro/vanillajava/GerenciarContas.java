package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciarContas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        ArrayList<ContaEspecial> contasEspeciais = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        boolean loggado = false;
        do {
            System.out.println("Olá, você já é cliente aqui? S/N");
            switch (scanner.next()) {
                case "S":
                    System.out.println("Qual o seu nome?");
                    String nome1 = scanner.next();
                    System.out.println("Poderia confirmar os ultimos 4 digitos do seu CPF?");
                    String cpf1 = scanner.next();
                    if (!clientes.contains(new Cliente(nome1, cpf1)))
                        System.out.println("Desculpe, não encontramos nenhum cliente com esses dados.");
                    else loggado = true;
                    break;
                case "N":
                    System.out.println("Vamos fazer o seu cadastro!");
                    System.out.println("Qual o seu nome?");
                    String nome2 = scanner.next();
                    System.out.println("Qual o seu CPF?");
                    String cpf2 = scanner.next();
                    Cliente cliente = new Cliente(nome2, cpf2);
                    if (!clientes.contains(cliente)) {
                        clientes.add(cliente);
                        System.out.println("Cadastro realizado com sucesso!");
                        loggado = true;
                    } else System.out.println("Não há nescessidade de você se cadstrar, pois você já está cadastrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!loggado);
    }

    ArrayList<Conta> contasCorrente = new ArrayList<>();
    ArrayList<Conta> contasEspeciais = new ArrayList<>();

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

}
