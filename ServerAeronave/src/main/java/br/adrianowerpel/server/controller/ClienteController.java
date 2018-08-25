package br.adrianowerpel.server.controller;

import br.adrianowerpel.server.dao.ClienteDAO;
import br.adrianowerpel.server.dao.InstituicaoFinanceiraDAO;
import br.adrianowerpel.server.dao.VoosDAO;
import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.InstituicaoFinanceira;
import br.adrianowerpel.server.models.Voos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteDAO clienteDAO;    
    @Autowired
    private VoosDAO vooDAO;
    
    public static Cliente Cliente;
    
    @RequestMapping(value = "/todosClientes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> ListarClientes(){
        
        List<Cliente> clientes = clienteDAO.getAll();
        
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/clientePorId/{id}", method = RequestMethod.GET)
    public Optional<Cliente> VooPorId(@PathVariable("id") Long id) throws ClassNotFoundException, SQLException{
        
        Optional<Cliente> cli = clienteDAO.ClientePorId(id);
        
        return cli;
    }
    
    @RequestMapping(value = "/novoCliente", method = RequestMethod.POST)
    public ResponseEntity<Cliente> NovoCliente(@RequestBody Cliente cliente){
        
        java.sql.Date dtSql = new java.sql.Date(cliente.getDtNascimento().getTime());
        cliente.setDtNascimento(dtSql);
                
        Cliente = clienteDAO.SaveOrUpdate(cliente);
        
        return new ResponseEntity<>(Cliente, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/novoVooCliente/{id}", method = RequestMethod.POST)
    public ResponseEntity<Voos> NovoVooCliente(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException{
        
        Voos voo = vooDAO.BuscarPorId(id);
        
        Cliente = clienteDAO.SaveOrUpdate(Cliente);
        
        return new ResponseEntity<>(voo, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletarCliente/{id}", method = RequestMethod.DELETE)
    public HttpStatus DeletarCliente(@PathVariable("id") Long id){
        
        try{
            clienteDAO.DeletarCliente(id);
            
            return  HttpStatus.OK;
            
        }catch(Exception e){
            return  HttpStatus.EXPECTATION_FAILED;
        }
    }
    
    @RequestMapping(value = "/editarCliente/", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> EditarCliente(@RequestBody Cliente cliente){
        
        cliente = clienteDAO.SaveOrUpdate(cliente);
        
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/buscarPorLogin/{login}&{senha}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> BuscarPorLogin(@PathVariable("login") String login,@PathVariable("senha") String senha) throws ClassNotFoundException, SQLException{
        
        Cliente = clienteDAO.BuscarPorLogin(login,senha);
        
        if(Cliente != null){
            return new ResponseEntity<>(Cliente, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FAILED_DEPENDENCY);
        }
    }   
}
