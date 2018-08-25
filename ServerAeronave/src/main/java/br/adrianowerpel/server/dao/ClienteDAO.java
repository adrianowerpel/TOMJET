package br.adrianowerpel.server.dao;

import br.adrianowerpel.server.connection.ConnectionFactory;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.InstituicaoFinanceira;
import br.adrianowerpel.server.models.Voos;
import br.adrianowerpel.server.repository.ClienteRepository;
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
public class ClienteDAO {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente SaveOrUpdate(Cliente cliente){
        
        cliente = clienteRepository.save(cliente);
        
        return cliente;
    }
    
    public List<Cliente> getAll(){
    
        return clienteRepository.findAll();
    }
    
    public void DeletarCliente(Long id){
        
        clienteRepository.deleteById(id);
    }
    
    public Optional<Cliente> ClientePorId(Long id) throws ClassNotFoundException, SQLException{
                
        return clienteRepository.findById(id);
    }
    
    public Cliente BuscarPorId(Long id) throws ClassNotFoundException, SQLException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        Cliente cli = null;
        
        if(rs.next())
        {
            cli = new Cliente();
            cli.setId(rs.getLong("id"));
            cli.setBairro(rs.getString("bairro"));
            cli.setCidade(rs.getString("cidade"));
            cli.setCpf(rs.getString("cpf"));
            cli.setDtNascimento(rs.getDate("dt_nascimento"));
            cli.setEstado(rs.getString("estado"));
            cli.setNumero(rs.getInt("numero"));            
            cli.setNome(rs.getString("nome"));
            cli.setLogin(rs.getString("login"));
            cli.setSenha(rs.getString("senha"));
            cli.setRua(rs.getString("rua"));
            cli.setTelefone(rs.getString("telefone"));            
        }
        
        return cli;
    }
    
    //Usado para o metodo de busca por Data
    public List<Cliente> BuscarPorVoo(Long id) throws ClassNotFoundException, SQLException{
          
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM cliente WHERE id_voo_cliente = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        List<Cliente> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Cliente cli = new Cliente();
            cli.setId(rs.getLong("id"));
            cli.setBairro(rs.getString("bairro"));
            cli.setCidade(rs.getString("cidade"));
            cli.setCpf(rs.getString("cpf"));
            cli.setDtNascimento(rs.getDate("dt_nascimento"));
            cli.setEstado(rs.getString("estado"));
            cli.setNumero(rs.getInt("numero"));            
            cli.setNome(rs.getString("nome"));
            cli.setLogin(rs.getString("login"));
            cli.setSenha(rs.getString("senha"));
            cli.setRua(rs.getString("rua"));
            cli.setTelefone(rs.getString("telefone"));
            
            lista.add(cli);            
        }
        
        return lista;
    }
    
    public Cliente BuscarPorLogin(String login,String senha) throws ClassNotFoundException, SQLException{
          
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM cliente WHERE login = ? and senha = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setString(1, login);
        pst.setString(2, senha);
        
        ResultSet rs = pst.executeQuery();
        
        Cliente cli = null;
        
        while(rs.next()){
            
            cli = new Cliente();
            cli.setId(rs.getLong("id"));
            cli.setBairro(rs.getString("bairro"));
            cli.setCidade(rs.getString("cidade"));
            cli.setCpf(rs.getString("cpf"));
            cli.setDtNascimento(rs.getDate("dt_nascimento"));
            cli.setEstado(rs.getString("estado"));
            cli.setNumero(rs.getInt("numero"));            
            cli.setNome(rs.getString("nome"));
            cli.setLogin(rs.getString("login"));
            cli.setSenha(rs.getString("senha"));
            cli.setRua(rs.getString("rua"));
            cli.setTelefone(rs.getString("telefone"));   
            
            InstituicaoFinanceiraDAO iDAO = new InstituicaoFinanceiraDAO();
            InstituicaoFinanceira banco = new InstituicaoFinanceira();
            banco = iDAO.BuscarPorIdCliente(rs.getLong("id"));
            cli.setBanco(banco);
        }
        
        return cli;
    }

}
