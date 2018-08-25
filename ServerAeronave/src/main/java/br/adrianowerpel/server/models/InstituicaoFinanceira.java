package br.adrianowerpel.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class InstituicaoFinanceira implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    private String numCartao;
    private double saldo;
    private Date dtValidade;
    
    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente cliente;

    public InstituicaoFinanceira() {
    }

    public InstituicaoFinanceira(Long id, String nome, String numCartao, double saldo, Date dtValidade, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.numCartao = numCartao;
        this.saldo = saldo;
        this.dtValidade = dtValidade;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

      
}

