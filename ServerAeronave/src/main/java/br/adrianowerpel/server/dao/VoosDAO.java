package br.adrianowerpel.server.dao;

import br.adrianowerpel.server.connection.ConnectionFactory;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.Passagens;
import br.adrianowerpel.server.models.Voos;
import br.adrianowerpel.server.repository.VoosRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoosDAO {
    
    @Autowired
    private VoosRepository voosRepository;

    public Voos SaveOrUpdate(Voos voos){
        
        voos = voosRepository.save(voos);
        
        return voos;
    }
    
    public List<Voos> getAll(){
    
        return voosRepository.findAll();
    }
    
    public void DeletarCliente(Long id){
        
        voosRepository.deleteById(id);
    }
    
    public Voos BuscarPorId(Long id) throws SQLException, ClassNotFoundException{
                
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM voos WHERE id = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        Voos voo = null;
        
        while(rs.next()){
            
            voo = new Voos();
            voo.setId(rs.getLong("id"));
            voo.setCidadeOrigem(rs.getString("cidade_origem"));
            voo.setCidadeDestino(rs.getString("cidade_destino"));
            voo.setDataSaida(rs.getTimestamp("data_saida"));
            voo.setDataChegada(rs.getTimestamp("data_chegada"));
            voo.setQtdAssentos(rs.getInt("qtd_assentos"));
            voo.setValorPassagem(rs.getDouble("valor_passagem"));
        }
        
        return voo;
    }
    
    //Usado para o Metodo de Busca por Data
    public List<Voos> BuscarPorData(String dataIda,String origem,String destino) throws ClassNotFoundException, SQLException{
          
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM voos WHERE data_saida like ? and cidade_origem like ? "
                + "and cidade_destino like ? and qtd_assentos > 0";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setString(1, "%"+dataIda+"%");
        pst.setString(2, "%"+origem+"%");
        pst.setString(3, "%"+destino+"%");
        
        ResultSet rs = pst.executeQuery();
        
        List<Voos> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Voos voo = new Voos();
            voo.setId(rs.getLong("id"));
            voo.setCidadeOrigem(rs.getString("cidade_origem"));
            voo.setCidadeDestino(rs.getString("cidade_destino"));
            voo.setDataSaida(rs.getTimestamp("data_saida"));
            voo.setDataChegada(rs.getTimestamp("data_chegada"));
            voo.setQtdAssentos(rs.getInt("qtd_assentos"));
            voo.setValorPassagem(rs.getDouble("valor_passagem"));
            
            PassagensDAO pDAO = new PassagensDAO();
            ArrayList<Passagens> passagens = new ArrayList<>();
            passagens = pDAO.PassagensPorVoo(rs.getLong("id"));
            voo.setPassagens(passagens);
            
            lista.add(voo);
        }

        
        return lista;
    }
    
    public List<Voos> VoosPorCliente(Long id) throws ClassNotFoundException, SQLException{
          
        Connection c = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM voos WHERE id = ?";
        
        PreparedStatement pst = c.prepareStatement(sql);
        
        pst.setLong(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        List<Voos> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Voos voo = new Voos();
            voo.setId(rs.getLong("id"));
            voo.setCidadeOrigem(rs.getString("cidade_origem"));
            voo.setDataSaida(rs.getTimestamp("data_saida"));
            voo.setCidadeDestino(rs.getString("cidade_destino"));
            voo.setDataChegada(rs.getTimestamp("data_chegada"));
            voo.setQtdAssentos(rs.getInt("qtd_assentos"));
            voo.setValorPassagem(rs.getDouble("valor_passagem"));
            
            lista.add(voo);
        }

        
        return lista;
    }
    
}
