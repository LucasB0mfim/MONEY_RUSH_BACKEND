package br.com.lbomfim.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lbomfim.finance.model.Usuario;

/**
 * @author Lucas Bomfim 
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}