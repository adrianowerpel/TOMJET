package br.adrianowerpel.server.repository;

import br.adrianowerpel.server.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
