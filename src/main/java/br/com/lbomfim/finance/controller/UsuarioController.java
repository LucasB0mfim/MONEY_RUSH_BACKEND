package br.com.lbomfim.finance.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lbomfim.finance.exception.UsuarioInvalidoException;
import br.com.lbomfim.finance.model.Usuario;
import br.com.lbomfim.finance.repository.UsuarioRepository;



/**
 * @author Lucas Bomfim 
 */

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

	// CHAMANDO O REPOSITORIO
	private UsuarioRepository repository;
	
	// CONSTRUTOR DO CONTROLLER
	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	// MÉTODO PARA CADASTRAR
	@PostMapping(path = "/cadastrar")
	public Usuario create(@RequestBody Usuario usuario) {
		LOGGER.info("Cadastrando usuario: {}", usuario.getNome());
		return repository.save(usuario);
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
	    Optional<Usuario> usuarioEncontrado = Optional.ofNullable(repository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()));

	    if (usuarioEncontrado.isPresent()) {
	        LOGGER.info("Usuário logado com sucesso: {}", usuarioEncontrado.get().getNome());
	        return ResponseEntity.ok(usuarioEncontrado.get()); // Retorna o usuário com o id
	    } else {
	        LOGGER.warn("Falha no login: usuário não encontrado para o email {}", usuario.getEmail());
	        throw new UsuarioInvalidoException(); // Lançando uma exceção para indicar erro
	    }
	}

	// MÉTODO PARA BUSCAR
	@GetMapping(path = "/buscar")
	public List<Usuario> findAll() {
		return repository.findAll();
	}
}
