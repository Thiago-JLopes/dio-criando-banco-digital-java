package org.bancodigital;

import org.bancodigital.model.entity.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    //MENUS
    public static void selecionarTipoContaCriar(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;


        int senha;
        do {
            //Seleciona o tipo de conta a ser criada
            System.out.println("Selecione o tipo de conta que deseja.");
            System.out.println("[1] Conta Corrente");
            System.out.println("[2] Conta Poupança");
            System.out.println("[0] Voltar");
            opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    System.out.println("Crie uma senha (somente números): ");
                    senha = scanner.nextInt();
                    ContaCorrente contaCorrente = new ContaCorrente(senha, LocalDateTime.now());
                    cliente.criarConta(contaCorrente);
                    System.out.println("Conta Corrente criada com sucesso.");
                    System.out.println("AGENCIA: 1");
                    System.out.println("CONTA: " + contaCorrente.getNumero());
                    opcoesContaCliente(cliente);
                    break;
                case 2:
                    System.out.println("Crie uma senha (somente números): ");
                    senha = scanner.nextInt();
                    ContaPoupanca contaPoupanca = new ContaPoupanca(senha, LocalDateTime.now());
                    cliente.criarConta(contaPoupanca);
                    System.out.println("Conta Poupança criada com sucesso.");
                    System.out.println("AGENCIA: 1");
                    System.out.println("CONTA: " + contaPoupanca.getNumero());
                    opcoesContaCliente(cliente);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    //Menu cliente
    public static void opcoesContaCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Acessar Conta.");

        //verifica dados de acesso
        System.out.print("NUMERO CONTA: ");
        Long numeroConta = scanner.nextLong();
        System.out.print("SENHA: ");
        int senha = scanner.nextInt();

        if(cliente.getConta(numeroConta) != null) {
            Conta contaCliente = cliente.getConta(numeroConta);
            if(contaCliente.validarAcesso(senha)) {

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
                                double valor = scanner.nextDouble();
                                contaCliente.sacar(valor);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                System.out.print("--Transferir--\nVALOR: ");
                                double valor = scanner.nextDouble();
                                //so para teste trasferir para uma conta do mesmo cliente
                                System.out.print("CONTA: ");
                                Long numeroContaDestino = scanner.nextLong();
                                contaCliente.tranferir(cliente.getConta(numeroContaDestino), valor);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("R$"+contaCliente.getSaldo());
                            break;
                        case 5:
                            contaCliente.exibirExtrato();
                            break;
                        case 6:
                            break;
                    }
                }while (opcao != 6);
            } else {
                System.out.println("Dados de acesso incorretos.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Criar uma agência bancaria
        Banco dioBanco = new Banco("BancoDIO",1, 3299911111L);

        //Menu
        int opcao = 0;
        do {
            System.out.println("--------------------TESTE A IMPLEMENTACAO--------------------");
            System.out.println("[1]Cadastrar novo cliente           [2]Listar Clientes\n" +
                    "[3]Dados da Agência                  [4]Sair");
            opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    Cliente novoCliente = dioBanco.cadastrarCliente();
                    selecionarTipoContaCriar(novoCliente);
                    break;
                case 2:
                    dioBanco.listarClientes();
                    break;
                case 3:
                    dioBanco.dadosAgencia();
                    break;
                case 4:
                    break;
            }
        }while (opcao != 4);


    }
}