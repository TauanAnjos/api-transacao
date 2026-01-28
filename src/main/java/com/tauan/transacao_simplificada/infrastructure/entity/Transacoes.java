package com.tauan.transacao_simplificada.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacao")
@Table
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "recebedor_id")
    private Usuario recebedor;
    @ManyToOne
    @JoinColumn(name = "pagador_id")
    private Usuario pagador;
    private LocalDateTime dataHoraTransacao;

    @PrePersist
    public void prePersist(){
        this.dataHoraTransacao = LocalDateTime.now();
    }

    public Transacoes() {
    }

    public Transacoes(Long id, BigDecimal valor, Usuario recebedor, Usuario pagador, LocalDateTime dataHoraTransacao) {
        this.id = id;
        this.valor = valor;
        this.recebedor = recebedor;
        this.pagador = pagador;
        this.dataHoraTransacao = dataHoraTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Usuario recebedor) {
        this.recebedor = recebedor;
    }

    public Usuario getPagador() {
        return pagador;
    }

    public void setPagador(Usuario pagador) {
        this.pagador = pagador;
    }

    public LocalDateTime getDataHoraTransacao() {
        return dataHoraTransacao;
    }

    public void setDataHoraTransacao(LocalDateTime dataHoraTransacao) {
        this.dataHoraTransacao = dataHoraTransacao;
    }
}
