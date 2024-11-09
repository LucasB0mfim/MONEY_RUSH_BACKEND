package br.com.lbomfim.finance.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lbomfim.finance.exception.UsuarioInvalidoException;
import br.com.lbomfim.finance.model.Despesa;
import br.com.lbomfim.finance.model.Usuario;
import br.com.lbomfim.finance.repository.DespesaRepository;
import br.com.lbomfim.finance.repository.UsuarioRepository;

/**
 * @author Lucas Bomfim 
 */

@RestController
@RequestMapping(path = "/despesa")
public class DespesaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DespesaController.class);
	
	private DespesaRepository despesaRepository;
	private UsuarioRepository usuarioRepository;
	
	// CONSTRUTOR PADRAO
	public DespesaController(DespesaRepository despesaRepository, UsuarioRepository usuarioRepository) {
		this.despesaRepository = despesaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	/**
	 * @apiNote
	 * A classe Optional serve para ver se o valor retornado existe ou não.
	 * É uma forma de evitar o NullPointerException.
	 * Você não é obrigado a iniciar o objeto com o Optional. 
	 * */
	
	// METODO PARA CADASTRAR DESPESA
	@PostMapping(path = "/cadastrar/{id}")
	public Despesa crete(@PathVariable("id") Long usuarioId, @RequestBody Despesa despesa) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if (usuario.isPresent()) {
			despesa.setUsuario(usuario.get());
			LOGGER.info("Gerando uma nova despesa de: {}", despesa.getUsuario().getNome());
			return despesaRepository.save(despesa);
		} else {
			throw new UsuarioInvalidoException();
		}
	}
	
	// METODO PARA BUSCAR
	@GetMapping(path = "/buscar/{id}")
	public List<Despesa> findByUsuarioId(@PathVariable("id") Long usuarioId) {
		
		if (usuarioRepository.existsById(usuarioId)) {
			return despesaRepository.findByUsuarioId(usuarioId);
		} else {
			throw new UsuarioInvalidoException();
		}
	}
	
}
