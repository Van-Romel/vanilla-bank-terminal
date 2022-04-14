package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GerenciaContas gerenciaContas = new GerenciaContas();
        ArrayList<Cliente> clientes = gerenciaContas.getClientes();
        ArrayList<ContaCorrente> contasCorrente = gerenciaContas.getContasCorrente();
        ArrayList<ContaEspecial> contasEspeciais = gerenciaContas.getContasEspeciais();
        boolean loggado = false, sair = false;
        do {
            System.out.println("Olá, você já é cliente aqui? S/N");
            switch (scanner.next()) {
                case "S":
                case "s":
                    System.out.println("Digite o seu CPF, por favor. (Apenas números)");
                    String cpf1 = scanner.next();
                    if (!clientes.contains(new Cliente(null, cpf1, null))) {
                        System.out.println("Desculpe, não encontramos nenhum cliente com este CPF.\n\n\n\n");
                        break;
                    } else {
                        for (int i = 0; i < 3; i++) {
                            System.out.println("Digite o seu PIN de acesso, por favor.");
                            String pin1 = scanner.next();

                            for (Cliente cliente : clientes) {
                                if (cliente.cpf.equals(cpf1) && cliente.pin.equals(pin1)) {
                                    System.out.println("Bem-vindo, " + cliente.nome + "!");
                                    loggado = true;
                                    break;
                                } else
                                    System.out.println("O PIN inserido é inválido.\n\n\n\n Você tem mais " + (2 - i) + " tentativas.");
                            }
                            if (i == 2) break;
                        }
                        break;
                    }
                case "N":
                case "n":
                    System.out.println("Vamos fazer o seu cadastro!\n\n");
                    System.out.println("Qual o seu CPF? (Apenas números)");
                    String cpf2 = scanner.next();
                    Cliente cliente = new Cliente(null, cpf2, null);
                    if (!clientes.contains(cliente)) {
                        System.out.println("Qual o seu nome completo?");
                        String nome2 = scanner.next();
                        System.out.println("Insira um PIN de acesso para sua conta.");
                        String pin2 = scanner.next();
                        gerenciaContas.cadastrarCliente(nome2, cpf2, pin2);
                        System.out.println("Cadastro realizado com sucesso!");
                        loggado = true;
                    } else {
                        String[] s = clientes.get(clientes.indexOf(cliente)).getNome().split(" ");
                        System.out.println("Encontrei um cadastro existente com este CPF, você é o/a "
                                + s[0] + " " + s[s.length - 1] + "? S/N");
                        switch (scanner.next()) {
                            case "S":
                            case "s":
                                for (int i = 0; i < 3; i++) {
                                    System.out.println("Digite o seu PIN de acesso, por favor.");
                                    String pin2 = scanner.next();

                                    for (Cliente cliente1 : clientes) {
                                        if (cliente1.cpf.equals(cpf2) && cliente1.pin.equals(pin2)) {
                                            System.out.println("Bem-vindo, " + cliente1.nome + "!");
                                            loggado = true;
                                            break;
                                        } else
                                            System.out.println("O PIN inserido é inválido.\n\n\n\n Você tem mais " + (2 - i) + " tentativas.");
                                    }
                                    if (i == 2) break;
                                }
                            case "N":
                            case "n":
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!loggado);

        do {
            System.out.println("\n\n\n\n\n\n\n\n\n");

        } while (!sair);

    }
}
