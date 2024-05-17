package org.bancodigital.model.entity;

import java.time.LocalDateTime;

public class ContaCorrente extends Conta{
    private Double chequeEspecial;

    public ContaCorrente(Integer senha, LocalDateTime dataCriacao) {
        super(senha, dataCriacao);
        this.chequeEspecial = 500d;
    }

    public Double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public void sacar(Double valor) {
        if(valor <= 0 ) {
            throw new RuntimeException("Valor inválido");
        } else {
            if(valor <= getSaldo()) {
                Double saldo = getSaldo() - valor;
                setSaldo(saldo);

                //adiciona evento ao extrato
                adicionarExtrato(new Extrato(LocalDateTime.now(), "SAQUE", valor, getSaldo(), "--saque--"));

                System.out.println("Saque concluído com sucesso.");
            } else if(valor <= getSaldo() + this.chequeEspecial) {
                this.chequeEspecial -= (valor - getSaldo());

                //adiciona evento ao extrato
                adicionarExtrato(new Extrato(LocalDateTime.now(), "SAQUE", valor, getSaldo(), "--saque--"));

                System.out.println("Saque concluído com sucesso, o valor de " + (valor - getSaldo()) + " foi consumido do cheque especial");
                setSaldo(0d);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        }

    }

    @Override
    public void tranferir(Conta contaDestino, Double valor) {
        if(valor > 0 && valor <= getSaldo()+this.chequeEspecial) {
            contaDestino.depositar(valor);
            //adiciona evento ao extrato conta destino
            contaDestino.adicionarExtrato(new Extrato(LocalDateTime.now(), "TRANSFERENCIA", valor, contaDestino.getSaldo(), "transferencia origem " + this.getNumero()));

            sacar(valor);
            //adiciona evento ao extrato
            adicionarExtrato(new Extrato(LocalDateTime.now(), "DEPOSITO", valor, getSaldo(),"deposito p/" + contaDestino.getNumero()));
        }
    }
}
