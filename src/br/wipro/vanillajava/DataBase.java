package br.wipro.vanillajava;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DataBase {

    GerenciaContas gerenciaContas = new GerenciaContas();
    ArrayList<Cliente> clientes = gerenciaContas.getClientes() != null ? gerenciaContas.getClientes() : new ArrayList<Cliente>();
    ArrayList<ContaCorrente> contasCorrente = gerenciaContas.getContasCorrente() != null ? gerenciaContas.getContasCorrente() : new ArrayList<ContaCorrente>();
    ArrayList<ContaEspecial> contasEspeciais = gerenciaContas.getContasEspeciais() != null ? gerenciaContas.getContasEspeciais() : new ArrayList<ContaEspecial>();

    public DataBase(GerenciaContas gerenciaContas) {
        this.gerenciaContas = gerenciaContas;
    }
}
