package br.adrianowerpel.server.controller;

import br.adrianowerpel.server.dao.ClienteDAO;
import br.adrianowerpel.server.dao.PassagensDAO;
import br.adrianowerpel.server.dao.VoosDAO;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.Passagens;
import br.adrianowerpel.server.models.Voos;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassagensController {
    
    @Autowired
    private PassagensDAO passagensDAO;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private VoosDAO voosDAO;
    
    @RequestMapping(value = "/todosPassagens", method = RequestMethod.GET)
    public ResponseEntity<List<Passagens>> ListarPassagens(){
        
        List<Passagens> pas = passagensDAO.getAll();
        
        return new ResponseEntity<>(pas, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/novaPassagem/{idCliente}&{idVoo}", method = RequestMethod.GET)
    public ResponseEntity<Passagens> NovaPassagem(@PathVariable("idCliente") Long idCliente,@PathVariable("idVoo") Long idVoo) throws ClassNotFoundException, SQLException{
        
        Cliente cli = clienteDAO.BuscarPorId(idCliente);
        Voos voo = voosDAO.BuscarPorId(idVoo);
        
        Passagens passagem = new Passagens();
        passagem.setCliente(cli);
        passagem.setVoo(voo);
        passagem.setDataCompra(new Date());
                
        passagem = passagensDAO.SaveOrUpdate(passagem);
        
        return new ResponseEntity<>(passagem, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/passagensPorCliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Passagens>> PassagemPorCliente(@PathVariable("id") Long id) throws ClassNotFoundException, SQLException{
        
        List<Passagens> passagens = passagensDAO.PassagensPorCliente(id);
        
        return new ResponseEntity<>(passagens, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletarPassagem/{id}", method = RequestMethod.DELETE)
    public HttpStatus DeletarPassagem(@PathVariable("id") Long id){
        
        try{
            passagensDAO.DeletarPassagem(id);
            
            return  HttpStatus.OK;
            
        }catch(Exception e){
            return  HttpStatus.EXPECTATION_FAILED;
        }
    }
}
