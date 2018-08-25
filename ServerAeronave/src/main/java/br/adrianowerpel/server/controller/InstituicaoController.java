package br.adrianowerpel.server.controller;

import br.adrianowerpel.server.dao.InstituicaoFinanceiraDAO;
import br.adrianowerpel.server.models.InstituicaoFinanceira;
import java.sql.SQLException;
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
public class InstituicaoController {
    
    @Autowired
    private InstituicaoFinanceiraDAO instituicaoDAO;
    
    @RequestMapping(value = "/todosBancos", method = RequestMethod.GET)
    public ResponseEntity<List<InstituicaoFinanceira>> ListarClientes(){
        
        List<InstituicaoFinanceira> bancos = instituicaoDAO.getAll();
        
        return new ResponseEntity<>(bancos, HttpStatus.OK);
    }    
    
    @RequestMapping(value = "/editarBanco", method = RequestMethod.PUT)
    public ResponseEntity<InstituicaoFinanceira> EditarCliente(@RequestBody InstituicaoFinanceira banco){
        
        banco.setCliente(ClienteController.Cliente);
        
        banco = instituicaoDAO.SaveOrUpdate(banco);
        
        return new ResponseEntity<>(banco, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/validarCompra/{valor}&{numCartao}", method = RequestMethod.GET)
    public ResponseEntity<InstituicaoFinanceira> BancoPorIdCliente(@PathVariable("valor") double valor,
            @PathVariable("numCartao") String numCartao) throws ClassNotFoundException, SQLException{
        
        InstituicaoFinanceira banco = instituicaoDAO.BuscarNumCartao(numCartao);
        
        if(banco.getSaldo() > valor){
            return new ResponseEntity<>(banco, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FAILED_DEPENDENCY);
        }
    } 
}
