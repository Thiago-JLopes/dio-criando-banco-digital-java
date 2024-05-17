package org.bancodigital.model.entity;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Conta {
    //atributos
    private Long numero;
    private Integer senha;
    private Double saldo;
    private LocalDateTime dataCriacao;
    private List<Extrato> extrato;

    // Conjunto para armazenar os números de conta já gerados
    private static Set<Long> numerosConta = new HashSet<>();

    public Conta(Integer senha, LocalDateTime dataCriacao) {
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.extrato = new LinkedList<>();
        this.saldo = 0.0;
        this.numero = gerarNumeroUnico();
    }

    private Long gerarNumeroUnico() {
        Random random = new Random();
        Long numeroGerado;

        do{
            numeroGerado = Math.abs(random.nextLong() % 100000L);
        } while (numerosConta.contains(numeroGerado));

        numerosConta.add(numeroGerado);
        return numeroGerado;
    }

    public Long getNumero() {
        return numero;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Double getSaldo() {
        return saldo;
    }

    protected void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    //métodos abstratos
    abstract void sacar(Double valor);
    abstract void tranferir(Conta contaDestino, Double valor);

    void depositar(Double valor) {
        if(valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso.");
            //adiciona evento ao extrato
            adicionarExtrato(new Extrato(LocalDateTime.now(), "SAQUE", valor, getSaldo(),"--saque--"));
        } else {
            throw new RuntimeException("Valor deve ser maior que 0.");
        }
    }

    void adicionarExtrato(Extrato novoExtrato) {
        this.extrato.add(novoExtrato);
    }

    void exibirExtrato() {
        System.out.println(this.extrato);
    }

}
