package br.com.lbomfim.finance.exception;

/**
 * @author Lucas Bomfim 
 */

public class EmailInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException() {
		super("O email não é valido.");
	}
	
}
