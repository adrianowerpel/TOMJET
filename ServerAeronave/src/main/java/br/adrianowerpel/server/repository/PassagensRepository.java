/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.adrianowerpel.server.repository;

import br.adrianowerpel.server.models.Passagens;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author patricia
 */
public interface PassagensRepository extends JpaRepository<Passagens, Long>{
    
}
