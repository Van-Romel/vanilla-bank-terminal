package br.wipro.vanillajava;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GerenciaContas gerenciaContas = new GerenciaContas();
        Cliente clienteLogado = null;
        boolean loggado = false, sairServico = false, sairOperacao = false;
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
                        Optional<Cliente> clienteOptional = gerenciaContas.confirmaLogin(clientes, cpf1, scanner);
                        if (clienteOptional.isPresent()) {
                            clienteLogado = clienteOptional.get();
                            loggado = true;
                            break;
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
                                Optional<Cliente> clienteOptional = gerenciaContas.confirmaLogin(clientes, cpf2, scanner);
                                if (clienteOptional.isPresent()) {
                                    clienteLogado = clienteOptional.get();
                                    loggado = true;
                                    break;
                                }
                                break;
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
            Cliente finalClienteLogado = clienteLogado;
            List<ContaCorrente> contasCorrenteDoUsuarioLogado = gerenciaContas.contasCorrente.stream()
                    .filter(conta -> conta.getCliente().equals(finalClienteLogado))
                    .collect(Collectors.toList());
            List<ContaEspecial> contasEspeciaisDoUsuarioLogado = gerenciaContas.contasEspeciais.stream()
                    .filter(conta -> conta.getCliente().equals(finalClienteLogado))
                    .collect(Collectors.toList());
            System.out.printf("O Sr./Sra. tem %d conta corrente e %d conta especial!%n%n",
                    contasCorrenteDoUsuarioLogado.size(), contasEspeciaisDoUsuarioLogado.size());
            System.out.println("Qual serviço o Sr./Sra. gostaria de fazer?\n\n");
            System.out.println("1 - Criar uma nova conta\n" +
                    "2 - Acessar uma conta\n" +
                    "3 - Remover uma conta\n" +
                    "4 - Consultar dados da conta\n" +
                    "5 - Sair\n");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Qual o tipo de conta?\n" +
                            "1 - Conta Corrente\n" +
                            "2 - Conta Especial\n");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println("Qual o número da conta?");
                            new ContaCorrente(scanner.nextInt(), 0d, UUID.randomUUID().toString(), clienteLogado);
                        case 2:
                            System.out.println("Qual o número da conta?");
                            new ContaEspecial(scanner.nextInt(), 0d, UUID.randomUUID().toString(), clienteLogado, 1000d);
                    }
                case 2:
                    ContaCorrente contaCorrenteSelecionada = null;
                    ContaEspecial contaEspecialSelecionada = null;
                    int count = 0;
                    for (ContaCorrente contaCorrente : contasCorrenteDoUsuarioLogado) {
                        System.out.println("Opção [" + ++count + "] - Conta Corrente: " + contaCorrente.getNumero());
                    }
                    for (ContaEspecial contaEspecial : contasEspeciaisDoUsuarioLogado) {
                        System.out.println("Opção [" + ++count + "] - Conta Especial: " + contaEspecial.getNumero());
                    }
                    System.out.println("Qual opção desejada?");
                    int i = scanner.nextInt();
                    if (i <= contasCorrenteDoUsuarioLogado.size())
                        contaCorrenteSelecionada = contasCorrenteDoUsuarioLogado.get(count - 1);
                    else
                        contaEspecialSelecionada = contasEspeciaisDoUsuarioLogado.get(count - contasCorrenteDoUsuarioLogado.size() - 1);
                    do {
                        System.out.println("Qual operação deseja realizar?\n" +
                                "1 - Sacar\n" +
                                "2 - Depositar\n" +
                                "3 - Transferir\n" +
                                "4 - Consultar saldo\n" +
                                "5 - Sair\n");
                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println("Qual o valor a ser sacado?");
                                try {
                                    double valor = scanner.nextDouble();
                                    if (contaCorrenteSelecionada != null) {
                                        contaCorrenteSelecionada.sacar(valor);
                                        System.out.printf("Foi sacado R$ %.2f, seu saldo atual é R$ %.2f%n",
                                                valor, contaCorrenteSelecionada.getSaldo());
                                    } else {
                                        contaEspecialSelecionada.sacar(valor);
                                        System.out.printf("Foi sacado R$ %.2f, seu saldo atual é R$ %.2f%n e você tem um limite de R$ %.2f%n",
                                                valor, contaEspecialSelecionada.getSaldo(), contaEspecialSelecionada.getLimite());
                                    }
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                            case 2:
                                System.out.println("Qual o valor a ser depositado?");
                                try {
                                    double valor = scanner.nextDouble();
                                    if (contaCorrenteSelecionada != null) {
                                        contaCorrenteSelecionada.depositar(valor);
                                        System.out.printf("Foi depositado R$ %.2f, seu saldo atual é R$ %.2f%n",
                                                valor, contaCorrenteSelecionada.getSaldo());
                                        break;
                                    }
                                    if (contaEspecialSelecionada != null) {
                                        contaEspecialSelecionada.depositar(valor);
                                        System.out.printf("Foi depositado R$ %.2f, seu saldo atual é R$ %.2f e você tem um limite de R$ %.2f%n",
                                                valor, contaEspecialSelecionada.getSaldo(), contaEspecialSelecionada.getLimite());
                                        break;
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                            case 3:
                                System.out.println("Qual o valor a ser transferido?");
                                try {
                                    double valor = scanner.nextDouble();
                                    if (contaCorrenteSelecionada != null && contaCorrenteSelecionada.getSaldo() >= valor) {
                                        System.out.println("Qual o número da conta de destino?");
                                        int numeroContaDestino = scanner.nextInt();

                                    }
                                }
                        }
                    } while (!sairOperacao);
            }
        } while (!sairServico);
    }
}
