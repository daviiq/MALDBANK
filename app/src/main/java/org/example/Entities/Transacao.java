package org.example.Entities;

import java.time.LocalDateTime;

public class Transacao {
    private final TipoTransacao tipo;
    private final double valor;
    private final LocalDateTime dataHora;
    private final String descricao;

    public Transacao(TipoTransacao tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - R$ %.2f - %s", dataHora, tipo, valor, descricao);
    }
}
