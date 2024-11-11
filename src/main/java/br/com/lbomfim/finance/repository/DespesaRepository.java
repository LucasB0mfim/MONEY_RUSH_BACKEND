package br.com.lbomfim.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lbomfim.finance.model.Despesa;

/**
 * @author Lucas Bomfim 
 */

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuario_Id(Long usuarioId); // Correção já feita
}
