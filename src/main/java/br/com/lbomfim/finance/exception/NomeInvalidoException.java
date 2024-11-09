package br.com.lbomfim.finance.exception;

/**
 * @author Lucas Bomfim 
 */

public class NomeInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NomeInvalidoException() {
		super("O nome deve ter no mínimo 3 letras.");
	}
	
}
