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
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue   
    private Long id;
    
    private String nome;
    private String login;
    private String senha;
    private String cpf;
    private String telefone;
    private Date dtNascimento;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    
    private InstituicaoFinanceira banco;
    
    @OneToMany(mappedBy = "cliente",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Passagens> passagens;  

    public Cliente() {
        this.passagens = new ArrayList<>();
    }

    public Cliente(Long id, String nome, String login, String senha, String cpf, String telefone, Date dtNascimento, String rua, int numero, String bairro, String cidade, String estado, InstituicaoFinanceira banco, List<Passagens> passagens) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.banco = banco;
        this.passagens = passagens;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public InstituicaoFinanceira getBanco() {
        return banco;
    }

    public void setBanco(InstituicaoFinanceira banco) {
        this.banco = banco;
    }

    public List<Passagens> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagens> passagens) {
        this.passagens = passagens;
    }
    
    
}