package br.adrianowerpel.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Passagens implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Date dataCompra;
    
    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idVoo")
    @JsonIgnore
    private Voos voo;

    public Passagens() {
    }

    public Passagens(Long id, Date dataCompra, Cliente cliente, Voos voo) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.voo = voo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Voos getVoo() {
        return voo;
    }

    public void setVoo(Voos voo) {
        this.voo = voo;
    }    
}