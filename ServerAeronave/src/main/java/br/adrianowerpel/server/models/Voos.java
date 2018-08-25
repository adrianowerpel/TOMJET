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
public class Voos implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String cidadeOrigem;
    private String cidadeDestino;
    private Date dataSaida;
    private Date dataChegada;
    private int qtdAssentos;
    private double valorPassagem;
    
    @OneToMany(mappedBy = "voo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Passagens> passagens;   

    public Voos() {
        this.passagens = new ArrayList<>();
    }

    public Voos(Long id, String cidadeOrigem, String cidadeDestino, Date dataSaida, Date dataChegada, int qtdAssentos, double valorPassagem, List<Passagens> passagens) {
        this.id = id;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.qtdAssentos = qtdAssentos;
        this.valorPassagem = valorPassagem;
        this.passagens = passagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    public double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(double valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public List<Passagens> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagens> passagens) {
        this.passagens = passagens;
    }

    
}