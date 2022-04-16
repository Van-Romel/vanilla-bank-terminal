package br.wipro.vanillajava;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        DataBase dataBase = new DataBase(new GerenciaContas());
        Cliente clienteLogado = null;
        boolean loggado = false, sairServico = false, sairOperacao = false;
        do {
            System.out.println("Olá, você já é cliente aqui? S/N");
            switch (scanner.next()) {
                case "S":
                case "s":
                    System.out.println("Digite o seu CPF, por favor. (Apenas números)");
                    String cpf1 = scanner.next();
                    if (!dataBase.clientes.contains(new Cliente(null, cpf1, null))) {
                        System.out.println("Desculpe, não encontramos nenhum cliente com este CPF.\n\n\n\n");
                        break;
                    } else {
                        Optional<Cliente> clienteOptional = dataBase.gerenciaContas.confirmaLogin(dataBase.clientes, cpf1, scanner);
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
                    if (!dataBase.clientes.contains(cliente)) {
                        System.out.println("Qual o seu nome completo?");
                        scanner.nextLine();
                        String nome2 = scanner.nextLine();
                        System.out.println("\nInsira um PIN de acesso para sua conta.");
                        String pin2 = scanner.next();
                        dataBase.gerenciaContas.cadastrarCliente(nome2, cpf2, pin2);
                        System.out.println("Cadastro realizado com sucesso!");
                        loggado = true;
                    } else {
                        String[] s = dataBase.clientes.get(dataBase.clientes.indexOf(cliente)).getNome().split(" ");
                        System.out.println("Encontrei um cadastro existente com este CPF, você é o/a "
                                + s[0] + " " + s[s.length - 1] + "? S/N");
                        switch (scanner.next()) {
                            case "S":
                            case "s":
                                Optional<Cliente> clienteOptional = dataBase.gerenciaContas.confirmaLogin(dataBase.clientes, cpf2, scanner);
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
            Optional<List<ContaCorrente>> contasCorrenteDoUsuarioLogado = Optional.of(dataBase.gerenciaContas.contasCorrente.stream()
                    .filter(conta -> conta.getCliente().equals(finalClienteLogado))
                    .collect(Collectors.toList()));
            Optional<List<ContaEspecial>> contasEspeciaisDoUsuarioLogado = Optional.of(dataBase.gerenciaContas.contasEspeciais.stream()
                    .filter(conta -> conta.getCliente().equals(finalClienteLogado))
                    .collect(Collectors.toList()));
            System.out.printf("O Sr./Sra. tem %d conta corrente e %d conta especial!%n%n",
                    contasCorrenteDoUsuarioLogado.isPresent() ? contasCorrenteDoUsuarioLogado.get().size() : "0",
                    contasEspeciaisDoUsuarioLogado.isPresent() ? contasEspeciaisDoUsuarioLogado.get().size() : "0");
            Thread.sleep(3000);
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
                            contasCorrenteDoUsuarioLogado.get().add(
                                    dataBase.gerenciaContas.inserirContaCorrente(scanner.nextInt(), 0d, UUID.randomUUID().toString(), clienteLogado));
                            break;
                        case 2:
                            System.out.println("Qual o número da conta?");
                            contasEspeciaisDoUsuarioLogado.get().add(
                                    dataBase.gerenciaContas.inserirContaEspecial(scanner.nextInt(), 0d, UUID.randomUUID().toString(), clienteLogado, 1000d));
                    }
                    System.out.println("Conta criada com sucesso!");
                    break;
                case 2:
                    ContaCorrente contaCorrenteSelecionada = null;
                    ContaEspecial contaEspecialSelecionada = null;
                    int count = 0;
                    for (ContaCorrente contaCorrente : contasCorrenteDoUsuarioLogado.get()) {
                        System.out.println("Opção [" + ++count + "] - Conta Corrente: " + contaCorrente.getNumero());
                    }
                    for (ContaEspecial contaEspecial : contasEspeciaisDoUsuarioLogado.get()) {
                        System.out.println("Opção [" + ++count + "] - Conta Especial: " + contaEspecial.getNumero());
                    }
                    if (count == 0) {
                        System.out.println("Não há contas para selecionar! O/A Sr./Sra. deve criar uma conta.");
                        break;
                    } else System.out.println("Qual opção desejada?");
                    int i = scanner.nextInt();
                    if (i <= contasCorrenteDoUsuarioLogado.get().size())
                        contaCorrenteSelecionada = contasCorrenteDoUsuarioLogado.get().get(count - 1);
                    else
                        contaEspecialSelecionada = contasEspeciaisDoUsuarioLogado.get().get(count - contasCorrenteDoUsuarioLogado.get().size() - 1);
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
                                        System.out.printf("Foi sacado R$ %.2f, seu saldo atual é R$ %.2f%n",
                                                valor, contaEspecialSelecionada.getSaldo());
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
                                    System.out.println("Qual o número da conta de destino?");
                                    int numeroContaDestino = scanner.nextInt();
                                    var nome1 = dataBase.contasCorrente.stream().findFirst()
                                            .filter(conta -> conta.getNumero() == numeroContaDestino).orElse(null).getCliente().getNome();
                                    var nome2 = dataBase.contasEspeciais.stream().findFirst()
                                            .filter(conta -> conta.getNumero() == numeroContaDestino).orElse(null).getCliente().getNome();
                                    System.out.println("Você confirma transferir R$ " + valor + " para a conta " + numeroContaDestino + "?" +
                                            "do títular" + nome1 != null ? nome1 : nome2 + " (S/N)");
                                    contaCorrenteSelecionada.transferir(dataBase, numeroContaDestino, valor);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                var a = contaCorrenteSelecionada == null ?
                                        contaEspecialSelecionada.getSaldo() : contaCorrenteSelecionada.getSaldo();
                                System.out.printf("Seu saldo atual é R$ %.2f%n", a);
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                sairOperacao = true;
                        }
                    } while (!sairOperacao);
                case 3:
                    System.out.println("Qual o número EXATO da conta a ser removida?");
                    int x = scanner.nextInt();
                    var contaCorrenteASerRemovida = dataBase.contasCorrente.stream().findFirst()
                            .filter(conta -> conta.getNumero() == x).orElse(null);
                    var contaEspecialASerRemovida = dataBase.contasEspeciais.stream().findFirst()
                            .filter(conta -> conta.getNumero() == x).orElse(null);

                    dataBase.gerenciaContas.removeConta(dataBase, contaCorrenteASerRemovida, contaEspecialASerRemovida);
                    System.out.println("Conta removida com sucesso!");
                    break;
                case 4:
                    System.out.println("Qual o número da conta?");
                    int z = scanner.nextInt();
                    var contaCorrenteASerConsultada = dataBase.contasCorrente.stream().findFirst()
                            .filter(conta -> conta.getNumero() == z).orElse(null);
                    var contaEspecialASerConsultada = dataBase.contasEspeciais.stream().findFirst()
                            .filter(conta -> conta.getNumero() == z).orElse(null);
                    System.out.println(dataBase
                            .gerenciaContas
                            .consultarDadosConta(contaCorrenteASerConsultada, contaEspecialASerConsultada));
                case 5:
                    System.out.println("Saindo...");
                    sairServico = true;
            }
        } while (!sairServico);
        System.out.println("\n\nAté a próxima! 😊🍦\n\n" +
                "VANILLA BANK - 2022");
        Thread.sleep(2000);
        System.out.println("\n\n\n\n\n\n\n\n");
    }
}
