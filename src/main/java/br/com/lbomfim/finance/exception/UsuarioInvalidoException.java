package br.com.lbomfim.finance.exception;

/**
 * @author Lucas Bomfim 
 */

public class UsuarioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException() {
        super("Usuário inválido.");
    }
}
