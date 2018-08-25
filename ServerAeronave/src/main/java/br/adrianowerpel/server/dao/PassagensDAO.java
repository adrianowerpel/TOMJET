package br.adrianowerpel.server.dao;

import br.adrianowerpel.server.connection.ConnectionFactory;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.Passagens;
import br.adrianowerpel.server.models.Voos;
import br.adrianowerpel.server.repository.PassagensRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassagensDAO {
    
    @Autowired
    private PassagensRepository passagensRepository;
    
    public Passagens SaveOrUpdate(Passagens passagem)
    {
        passagem = passagensRepository.save(passagem);
        
        return passagem;
    }
    
    public List<Passagens> getAll(){
    
        return passagensRepository.findAll();
    }
    
    public void DeletarPassagem(Long id){
        
        passagensRepository.deleteById(id);
    }
    
    public ArrayList<Passagens> PassagensPorVoo (Long id) throws ClassNotFoundException, SQLException{
        
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM passagens WHERE id_voo = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1,id);
        
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Passagens> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Passagens p = new Passagens();
            p.setId(rs.getLong("id"));
            p.setDataCompra(rs.getTimestamp("data_compra"));
            
            lista.add(p);
        }
        
        return lista;
    }
    
    public ArrayList<Passagens> PassagensPorCliente (Long id) throws ClassNotFoundException, SQLException{
        
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM passagens WHERE id_cliente = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1,id);
        
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Passagens> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Passagens p = new Passagens();
            p.setId(rs.getLong("id"));
            p.setDataCompra(rs.getTimestamp("data_compra"));
            
            VoosDAO vDAO = new VoosDAO();
            Voos v = new Voos();
            v = vDAO.BuscarPorId(rs.getLong("id_voo"));
            p.setVoo(v);
            
            ClienteDAO cDAO = new ClienteDAO();
            Cliente cli = cDAO.BuscarPorId(rs.getLong("id_cliente"));
            p.setCliente(cli);
            
            lista.add(p);
        }
        
        return lista;
    }
    
}
