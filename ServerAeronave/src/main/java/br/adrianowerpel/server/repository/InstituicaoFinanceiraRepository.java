package br.adrianowerpel.server.repository;

import br.adrianowerpel.server.models.Cliente;
import br.adrianowerpel.server.models.InstituicaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, Long>{
    
}
