package com.tauan.transacao_simplificada.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "carteira")
@Table
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Carteira() {
    }

    public Carteira(Long id, BigDecimal saldo, Usuario usuario) {
        this.id = id;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
