package org.bancodigital.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private String nome;
    private String cpf;
    private String logradouro;
    private String cep;
    private String cidade;
    private Map<Long,Conta> contasCliente;

    //construtor default
    public Cliente() {
        this.contasCliente = new HashMap<>();
    }

    public Cliente(String nome, String cpf, String logradouro, String cep, String cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.contasCliente = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public Conta getConta(Long numeroConta) {
        return contasCliente.get(numeroConta);
    }

    public Map<Long, Conta> getContasCliente() {
        return contasCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void criarConta(Conta conta) {
        contasCliente.put(conta.getNumero(), conta);
        System.out.println("Conta criada com sucesso.");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

