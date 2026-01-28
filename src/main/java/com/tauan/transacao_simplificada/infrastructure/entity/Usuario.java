package com.tauan.transacao_simplificada.infrastructure.entity;

import jakarta.persistence.*;

@Entity(name = "usuario")
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String cpfCnpj;
    private String senha;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carteira carteira;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(Long id, String nomeCompleto, String email, String cpfCnpj, String senha, Carteira carteira) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.senha = senha;
        this.carteira = carteira;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
