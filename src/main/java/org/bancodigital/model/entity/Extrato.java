package org.bancodigital.model.entity;

import java.time.LocalDateTime;

public class Extrato {
    private LocalDateTime dataHora;
    private String tipoTransacao;
    private Double valor;
    private Double saldoAtual;
    private String descricao;

    public Extrato(LocalDateTime dataHora, String tipoTransacao, Double valor, Double saldoAtual, String descricao) {
        this.dataHora = dataHora;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.saldoAtual = saldoAtual;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "EXTRATO " +
                "dataHora " + dataHora +
                "\n    Transacao " + tipoTransacao +
                "\n    Valor " + valor +
                "\n    Saldo Atual " + saldoAtual +
                "\n    Descricao " + descricao + "\n\n";
    }
}
