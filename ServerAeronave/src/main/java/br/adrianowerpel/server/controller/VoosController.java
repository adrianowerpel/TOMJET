package br.adrianowerpel.server.controller;

import br.adrianowerpel.server.dao.VoosDAO;
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
public class VoosController {
    
    @Autowired
    private VoosDAO voosDAO;
    
    @RequestMapping(value = "/todosVoos", method = RequestMethod.GET)
    public ResponseEntity<List<Voos>> ListarVoos(){
        
        List<Voos> voos = voosDAO.getAll();
        
        List<Voos> voosAux = new ArrayList<>();
        
        for(Voos v: voos)
        {
            if(v.getQtdAssentos() > 0)
            {
                voosAux.add(v);
            }
        }
        
        return new ResponseEntity<>(voosAux, HttpStatus.OK);
    }   
    
    @RequestMapping(value = "/editarVoo", method = RequestMethod.PUT)
    public ResponseEntity<Voos> EditarVoo(@RequestBody Voos voo){
             
        voo = voosDAO.SaveOrUpdate(voo);
        
        return new ResponseEntity<>(voo, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/vooPorData/{origem}&{dataIda}&{destino}", method = RequestMethod.GET)
    public ResponseEntity<List<Voos>> VooPorData(@PathVariable("dataIda") String dataIda,
            @PathVariable("origem") String origem,@PathVariable("destino") String destino) throws ClassNotFoundException, SQLException{
        
        List<Voos> voos = voosDAO.BuscarPorData(dataIda,origem,destino);
        
        List<Voos> voosAux = new ArrayList<>();
        
        for(Voos v: voos)
        {
            if(v.getQtdAssentos() > 0)
            {
                voosAux.add(v);
            }
        }
        
        return new ResponseEntity<>(voosAux, HttpStatus.OK);
    }
}
