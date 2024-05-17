package org.bancodigital;

import org.bancodigital.model.entity.Banco;
import org.bancodigital.model.entity.Cliente;
import org.bancodigital.model.entity.ContaCorrente;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    //MENUS
    public static void selecionarTipoContaCriar(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        //Seleciona o tipo de conta a ser criada
        System.out.println("Selecione o tipo de conta que deseja.");
        System.out.println("[1] Conta Corrente");
        System.out.println("[2] Conta Poupança");
        System.out.println("[0] Sair");

        int senha;
        do {
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
                    opcao = 0;
                    break;
                case 2:
                    System.out.println("Crie uma senha (somente números): ");
                    senha = scanner.nextInt();
                    cliente.criarConta(new ContaCorrente(senha, LocalDateTime.now()));
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
        scanner.close();
    }

    //Menu cliente
    public static void opcoesContaCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Acessar Conta.");

        //verifica dados de acesso
        System.out.print("NUMERO CONTA: ");
        Long numeroConta = scanner.nextLong();
        System.out.println("SENHA: ");
        int senha = scanner.nextInt();

        if(cliente.getConta(numeroConta) != null) {
            if(cliente.getConta(numeroConta).validarAcesso(senha)) {

                int opcao = 0;
                do {
                    System.out.println("[1]Depositar" +
                            "       [2]Sacar\n" +
                            "[3]Transferir      [4]Saldo\n" +
                            "[5]Extrato         [6]Sair");
                    opcao = scanner.nextInt();
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
        //cria um cliente
        Cliente novoCliente = new Cliente();

        //REALIZA CADASTRO DO CLIENTE NA AGENCIA BANCARIA
        System.out.println("--------------------" +dioBanco.getNome().toUpperCase()+"--------------------");
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

        System.out.println("----------------------------------------------");
        selecionarTipoContaCriar(novoCliente);
    }
}