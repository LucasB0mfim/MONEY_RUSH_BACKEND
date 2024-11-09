package br.com.lbomfim.finance.exception;

/**
 * @author Lucas Bomfim 
 */

public class SenhaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super("A senha não está de acordo com os requisitos mínimos.");
	}
	
}
