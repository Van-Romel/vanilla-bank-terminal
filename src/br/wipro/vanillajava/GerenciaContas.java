package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaContas {

    public static void main(String[] args) {

        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();
        ArrayList<ContaEspecial> contasEspeciais = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        int escolha;
        int verificaCliente;
        int verificaConta;
        String cpf;
        int numeroConta;
        String NumeroCartaoCredito;
        double deposito;
        double saque;

        do {
            System.out.println("Escolha qual ação quer realizar das opçoes abaixo");
            System.out.println("1: Cadastrar Cliente");
            System.out.println("2: Cadastrar Conta Corrente");
            System.out.println("3: Cadastrar Conta Especial");
            System.out.println("4: Consultar dados de uma conta corrente");
            System.out.println("5: Consultar dados de uma conta especial");
            System.out.println("6: Realizar deposito em conta corrente");
            System.out.println("7: Realizar deposito em conta especial");
            System.out.println("8: Realizar saque em conta corrente");
            System.out.println("9: Realizar saque em conta especial");
            System.out.println("10: Remover conta corrente");
            System.out.println("11: Remover conta especial");
            System.out.println("12: Para sair");

            escolha = s.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Insira o nome do cliente");
                    String nome = s.next();
                    System.out.println("Insira o CPF do cliente");
                    cpf = s.next();
                    Cliente inserirCliente = new Cliente(nome, cpf);
                    clientes.add(inserirCliente);
                    System.out.println("Cliente cadastrado");

                    break;
                case 2:
                    verificaCliente=0;
                    System.out.println("Insira o número da conta");
                    numeroConta = s.nextInt();
                    System.out.println("Insira o número do cartao de credito");
                    NumeroCartaoCredito = s.next();
                    System.out.println("Insira o CPF do cliente");
                    cpf = s.next();
                    for(Cliente cliente : clientes){
                        if(cliente.getCpf().equals(cpf)){
                            verificaCliente=1;
                        }
                    }
                    if(verificaCliente==0){
                        System.out.println("Cliente não cadastrado");
                        break;
                    }
                    ContaCorrente inserirContacorrente = new ContaCorrente(numeroConta, 0,NumeroCartaoCredito,cpf);
                    contasCorrente.add(inserirContacorrente);
                    System.out.println("Conta cadastrada");
                    break;
                case 3:
                    verificaCliente=0;
                    System.out.println("Insira o número da conta");
                    numeroConta = s.nextInt();
                    System.out.println("Insira o número do cartao de credito");
                    NumeroCartaoCredito = s.next();
                    System.out.println("Insira o CPF do cliente");
                    cpf = s.next();
                    for(Cliente cliente : clientes){
                        if(cliente.getCpf().equals(cpf)){
                            verificaCliente=1;
                        }
                    }
                    if(verificaCliente==0){
                        System.out.println("Cliente não cadastrado");
                        break;
                    }
                    System.out.println("Insira o valor de Limite da Conta");
                    Double limite = s.nextDouble();

                    ContaEspecial inserirContaEspecial = new ContaEspecial(numeroConta, 0,NumeroCartaoCredito,cpf,limite);
                    contasEspeciais.add(inserirContaEspecial);
                    System.out.println("Conta cadastrada");
                    break;
                case 4:
                    verificaConta=0;
                    System.out.println("Insira o número da conta a ser consultada");
                    numeroConta = s.nextInt();
                    for(ContaCorrente contaCorrente : contasCorrente){
                        if(contaCorrente.getNumero()==numeroConta){
                            contaCorrente.dadosConta();
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 5:
                    verificaConta=0;
                    System.out.println("Insira o número da conta a ser consultada");
                    numeroConta = s.nextInt();
                    for(ContaEspecial contaEspecial : contasEspeciais){
                        if(contaEspecial.getNumero()==numeroConta){
                            contaEspecial.dadosConta();
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 6:
                    verificaConta=0;
                    System.out.println("Insira o número da conta para deposito");
                    numeroConta = s.nextInt();
                    for(ContaCorrente contaCorrente : contasCorrente){
                        if(contaCorrente.getNumero()==numeroConta){
                            System.out.println("Insira o valor a ser depositado");
                            deposito = s.nextDouble();
                            contaCorrente.depositar(deposito);
                            verificaConta=1;
                            System.out.println("Deposito realizado");
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 7:
                    verificaConta=0;
                    System.out.println("Insira o número da conta para deposito");
                    numeroConta = s.nextInt();
                    for(ContaEspecial contaEspecial : contasEspeciais){
                        if(contaEspecial.getNumero()==numeroConta){
                            System.out.println("Insira o valor a ser depositado");
                            deposito = s.nextDouble();
                            contaEspecial.depositar(deposito);
                            verificaConta=1;
                            System.out.println("Deposito realizado");
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 8:
                    verificaConta=0;
                    System.out.println("Insira o número da conta para saque");
                    numeroConta = s.nextInt();
                    for(ContaCorrente contaCorrente : contasCorrente){
                        if(contaCorrente.getNumero()==numeroConta){
                            System.out.println("Insira o valor a ser sacado");
                            saque = s.nextDouble();
                            contaCorrente.sacar(saque);
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 9:
                    verificaConta=0;
                    System.out.println("Insira o número da conta para saque");
                    numeroConta = s.nextInt();
                    for(ContaEspecial contaEspecial : contasEspeciais){
                        if(contaEspecial.getNumero()==numeroConta){
                            System.out.println("Insira o valor a ser sacado");
                            saque = s.nextDouble();
                            contaEspecial.sacar(saque);
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 10:
                    verificaConta=0;
                    System.out.println("Insira o número da conta a ser removida");
                    numeroConta = s.nextInt();
                    for(ContaCorrente contaCorrente : contasCorrente){
                        if(contaCorrente.getNumero()==numeroConta){
                            contasCorrente.remove(contaCorrente);
                            System.out.println("Conta removida");
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 11:
                    verificaConta=0;
                    System.out.println("Insira o número da conta a ser removida");
                    numeroConta = s.nextInt();
                    for(ContaEspecial contaEspecial : contasEspeciais){
                        if(contaEspecial.getNumero()==numeroConta){
                            contasEspeciais.remove(contaEspecial);
                            System.out.println("Conta removida");
                            verificaConta=1;
                            break;
                        }
                    }
                    if(verificaConta==0){
                        System.out.println("Número da conta não existe");
                        break;
                    }
                    break;
                case 12:

                    break;
                default:
                    System.out.println("Opção invalida");

            }
        } while (escolha != 12);
    }
}
