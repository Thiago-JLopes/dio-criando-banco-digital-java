package org.bancodigital.model.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {
    private String nome;
    private Integer numeroAgemcia;
    private Long telefone;
    private Map<String,Cliente> clientes;

    public Banco(String nome, Integer numeroAgemcia, Long telefone) {
        this.nome = nome;
        this.numeroAgemcia = numeroAgemcia;
        this.telefone = telefone;
        this.clientes = new HashMap<>();

        System.out.println("Agência bancaria criada com sucesso.");
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumeroAgemcia() {
        return numeroAgemcia;
    }

    public Long getTelefone() {
        return telefone;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void adicionarCliente(Cliente novoCliente) {
        clientes.put(novoCliente.getCpf(), novoCliente);
        System.out.println("Registro criado com sucesso!");
    }

    public void removerCliente(String cpf) {
        if(clientes.isEmpty()) {
            throw new RuntimeException("Não há clientes para remover");
        } else {
            if(clientes.containsKey(cpf)) {
                clientes.remove(cpf);
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        }
    }

    public void listarClientes() {
        System.out.println(getClientes());
    }


}
