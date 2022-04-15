package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DataBase {

    GerenciaContas gerenciaContas;
    ArrayList<Cliente> clientes = gerenciaContas.getClientes();
    ArrayList<ContaCorrente> contasCorrente = gerenciaContas.getContasCorrente();
    ArrayList<ContaEspecial> contasEspeciais = gerenciaContas.getContasEspeciais();
    ArrayList<Conta> contas = Stream.of(contasCorrente, contasEspeciais).flatMap(Stream::of)
            .collect(ArrayList::new, (conta) -> this.contas.add(conta), ArrayList::addAll);

    public DataBase(GerenciaContas gerenciaContas) {
        this.gerenciaContas = gerenciaContas;
    }
}
