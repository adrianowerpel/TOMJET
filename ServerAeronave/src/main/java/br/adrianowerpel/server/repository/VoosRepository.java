package br.adrianowerpel.server.repository;

import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.Voos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoosRepository extends JpaRepository<Voos, Long>{
    
}
