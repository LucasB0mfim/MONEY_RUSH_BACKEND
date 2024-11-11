package br.com.lbomfim.finance.model;

import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.lbomfim.finance.exception.EmailInvalidoException;
import br.com.lbomfim.finance.exception.NomeInvalidoException;
import br.com.lbomfim.finance.exception.SenhaInvalidaException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * @author Lucas Bomfim
 */

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, unique = true, length = 80)
	private String nome;

	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true, length = 20)
	private String username;

	@Column(nullable = false)
	private String senha;
	
	@Column
	private Double salario;
	
	@OneToMany(mappedBy = "usuario")
	@JsonManagedReference
	private List<Despesa> despesas;

	// CONSTRUTOR VAZIO
	public Usuario() {

	}

	// CONSTRUTOR PADRAO
	public Usuario(Long id, String nome, String email, String username, String senha, Double salario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.username = username;
		this.senha = senha;
		this.salario = salario;
	}

	// GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.length() < 3) {
			throw new NomeInvalidoException();
		}
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws EmailInvalidoException {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	    Pattern pattern = Pattern.compile(emailRegex);

	    if (email == null || !pattern.matcher(email).matches()) {
	        throw new EmailInvalidoException();
	    }
	    this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws NomeInvalidoException {
		if (username == null || username.length() < 3) {
			throw new NomeInvalidoException();
		}
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws SenhaInvalidaException {
		if (senha == null || senha.length() < 8 
				|| !senha.matches(".*[A-Z].*") 
				|| !senha.matches(".*[a-z].*")
				|| !senha.matches(".*\\d.*") 
				|| !senha.matches(".*[!@#$%^&*].*")) {
			throw new SenhaInvalidaException();
		}
		this.senha = senha;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	
}
