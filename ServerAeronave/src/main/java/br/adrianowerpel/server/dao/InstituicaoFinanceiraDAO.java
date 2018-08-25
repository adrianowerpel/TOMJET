package br.adrianowerpel.server.dao;

import br.adrianowerpel.server.connection.ConnectionFactory;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.InstituicaoFinanceira;
import br.adrianowerpel.server.repository.InstituicaoFinanceiraRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoFinanceiraDAO {
    
    @Autowired
    private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    public InstituicaoFinanceira SaveOrUpdate(InstituicaoFinanceira instituicao){
        
        instituicao = instituicaoFinanceiraRepository.save(instituicao);
        
        return instituicao;
    }
    
    public List<InstituicaoFinanceira> getAll(){
    
        return instituicaoFinanceiraRepository.findAll();
    }
    
    public InstituicaoFinanceira BuscarPorId(Long id) throws ClassNotFoundException, SQLException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM instituicao_financeira WHERE id = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        InstituicaoFinanceira banco = null;
        
        if(rs.next())
        {
            banco = new InstituicaoFinanceira();
            banco.setId(rs.getLong("id"));
            banco.setDtValidade(rs.getDate("dt_validade"));
            banco.setNome(rs.getString("nome"));
            banco.setNumCartao(rs.getString("num_cartao"));
            banco.setSaldo(rs.getDouble("saldo"));
            
        }
        
        return banco;
    }
    
    public InstituicaoFinanceira BuscarNumCartao(String numCartao) throws ClassNotFoundException, SQLException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM instituicao_financeira WHERE num_cartao = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setString(1, numCartao);
        
        ResultSet rs = pst.executeQuery();
        
        InstituicaoFinanceira banco = null;
        
        if(rs.next())
        {
            banco = new InstituicaoFinanceira();
            banco.setId(rs.getLong("id"));
            banco.setDtValidade(rs.getDate("dt_validade"));
            banco.setNome(rs.getString("nome"));
            banco.setNumCartao(rs.getString("num_cartao"));
            banco.setSaldo(rs.getDouble("saldo"));
            
        }
        
        return banco;
    }
    
    public InstituicaoFinanceira BuscarPorIdCliente(Long id) throws ClassNotFoundException, SQLException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM instituicao_financeira WHERE id_cliente = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        InstituicaoFinanceira banco = null;
        
        if(rs.next())
        {
            banco = new InstituicaoFinanceira();
            banco.setId(rs.getLong("id"));
            banco.setDtValidade(rs.getDate("dt_validade"));
            banco.setNome(rs.getString("nome"));
            banco.setNumCartao(rs.getString("num_cartao"));
            banco.setSaldo(rs.getDouble("saldo"));            
        }
        
        return banco;
    }
    
    public InstituicaoFinanceira BancoPorCliente(Long id) throws ClassNotFoundException, SQLException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM instituicao_financeira WHERE id_cliente = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        InstituicaoFinanceira banco = null;
        
        if(rs.next())
        {
            banco = new InstituicaoFinanceira();
            banco.setId(rs.getLong("id"));
            banco.setDtValidade(rs.getDate("dt_validade"));
            banco.setNome(rs.getString("nome"));
            banco.setNumCartao(rs.getString("num_cartao"));
            banco.setSaldo(rs.getDouble("saldo"));    
            
            ClienteDAO cDAO = new ClienteDAO();
            Cliente cli = new Cliente();
            cli = cDAO.BuscarPorId(rs.getLong("id_cliente"));
            banco.setCliente(cli);
        }
        
        return banco;
    }
}
