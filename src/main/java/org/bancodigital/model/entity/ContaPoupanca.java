package org.bancodigital.model.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ContaPoupanca extends Conta{
    private Double rendimento;
    public ContaPoupanca(Integer senha, LocalDateTime dataCriacao) {
        super(senha, dataCriacao);
        this.rendimento = 0.05;
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
            } else {
                System.out.println("Saldo insuficiente.");
            }
        }

    }

    @Override
    public void tranferir(Conta contaDestino, Double valor) {
        if(valor > 0 && valor <= getSaldo()) {
            contaDestino.depositar(valor);
            //adiciona evento ao extrato conta destino
            contaDestino.adicionarExtrato(new Extrato(LocalDateTime.now(), "TRANSFERENCIA", valor, contaDestino.getSaldo(), "transferencia origem " + this.getNumero()));

            sacar(valor);
            //adiciona evento ao extrato
            adicionarExtrato(new Extrato(LocalDateTime.now(), "DEPOSITO", valor, getSaldo(),"deposito p/" + contaDestino.getNumero()));
        }
    }

    public void calcularRendimento() {
        // Assume que o rendimento é uma taxa mensal
        LocalDateTime agora = LocalDateTime.now();
        long meses = ChronoUnit.MONTHS.between(getDataCriacao(), agora);

        if (meses > 0) {
            double saldoAtual = getSaldo();
            double saldoComRendimento = saldoAtual * Math.pow((1 + rendimento), meses);
            setSaldo(saldoComRendimento);
            adicionarExtrato(new Extrato(agora, "Rendimento", saldoComRendimento - saldoAtual,getSaldo(), "Rendimento acumulado por " + meses + " meses"));
            System.out.println("Rendimento calculado e adicionado ao saldo.");
        } else {
            System.out.println("Ainda não passou um mês desde a criação da conta.");
        }
    }
}
