package org.bancodigital.model.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Banco {
    private String nome;
    private Integer numeroAgencia;
    private Long telefone;
    private Map<String, Cliente> clientes;

    public Banco(String nome, Integer numeroAgencia, Long telefone) {
        this.nome = nome;
        this.numeroAgencia = numeroAgencia;
        this.telefone = telefone;
        this.clientes = new HashMap<>();

        System.out.println("Agência bancaria criada com sucesso.");
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public Long getTelefone() {
        return telefone;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Cliente cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);
        //cria um cliente
        Cliente novoCliente = new Cliente();

        //REALIZA CADASTRO DO CLIENTE NA AGENCIA BANCARIA
        System.out.println("--------------------" + this.getNome().toUpperCase() + "--------------------");
        System.out.println("Para abrir a sua conta preenha a ficha a seguir.");
        //solicita os dados do usuario
        System.out.print("CPF: ");
        novoCliente.setCep(scanner.nextLine());
        System.out.print("NOME: ");
        novoCliente.setNome(scanner.nextLine().toUpperCase());
        System.out.print("CEP: ");
        novoCliente.setCep(scanner.nextLine());
        System.out.print("CIDADE: ");
        novoCliente.setCidade(scanner.nextLine().toUpperCase());
        System.out.print("LOGRADOURO: ");
        novoCliente.setLogradouro(scanner.nextLine().toUpperCase());

        clientes.put(novoCliente.getCpf(), novoCliente);
        System.out.println("Registro criado com sucesso!");

        System.out.println("----------------------------------------------");
        return novoCliente;
    }

    public void removerCliente(String cpf) {
        if (clientes.isEmpty()) {
            throw new RuntimeException("Não há clientes para remover");
        } else {
            if (clientes.containsKey(cpf)) {
                clientes.remove(cpf);
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        }
    }

    public void listarClientes() {
        System.out.println(clientes);
    }

    private int numerosClientes() {
        int quantidadeContas = 0;
        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            quantidadeContas += entry.getValue().getContasCliente().size();
        }
        return quantidadeContas;
    }

    public void dadosAgencia() {
        System.out.println("------------------------------------------");
        System.out.println("Razão Social: " + this.getNome());
        System.out.println("Número Agência: " + this.getNumeroAgencia());
        System.out.println("Telefone: " + this.getTelefone());
        System.out.println("Números Clientes: " + this.clientes.size());
        System.out.println("Número de Contas: " + numerosClientes());
    }

    private Conta buscarConta(Long numero) {
        if(clientes.isEmpty()) {
            throw new RuntimeException("Sem clientes");
        }

        Conta contaDestino = null;
        for (Map.Entry<String,Cliente>entry : clientes.entrySet()) {
            if(entry.getValue().getContasCliente().containsKey(numero)) {
                contaDestino = entry.getValue().getConta(numero);
            }
        }
        return contaDestino;
    }

    public boolean acessarConta(Cliente cliente) {
        //Menu cliente
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Acessar Conta.");

        //verifica dados de acesso
        System.out.print("NUMERO CONTA: ");
        Long numeroConta = scanner.nextLong();
        System.out.print("SENHA: ");
        int senha = scanner.nextInt();

        if (cliente.getConta(numeroConta) != null) {
            Conta contaCliente = cliente.getConta(numeroConta);
            if (contaCliente.validarAcesso(senha)) {
                int opcao = 0;
                do {
                    System.out.println("[1]Depositar" +
                            "       [2]Sacar\n" +
                            "[3]Transferir      [4]Saldo\n" +
                            "[5]Extrato         [6]Sair");
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            try {
                                System.out.print("--Deposito--\nVALOR: ");
                                double valor = scanner.nextDouble();
                                contaCliente.depositar(valor);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                System.out.print("--Saque--\nVALOR: ");
                                Double valor = scanner.nextDouble();
                                contaCliente.sacar(valor);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                System.out.print("--Transferir--\nVALOR: ");
                                double valor = scanner.nextDouble();
                                System.out.print("NÚMERO CONTA: ");
                                Long numeroContaDestino = scanner.nextLong();
                                Conta contaDestino = buscarConta(numeroContaDestino);

                                if(contaDestino != null) {
                                    contaCliente.tranferir(contaDestino,valor);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("R$" + contaCliente.getSaldo());
                            break;
                        case 5:
                            contaCliente.exibirExtrato();
                            break;
                        case 6:
                            break;
                    }
                } while (opcao != 6);
            } else {
                System.out.println("Dados de acesso incorretos.");
                return false;
            }
        }
        return true;
    }
}
