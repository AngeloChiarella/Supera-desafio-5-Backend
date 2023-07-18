package br.com.banco.entities.dtos;

public class ContaDto {

	private String nome;
	private int agencia;
	private int numero;

	public ContaDto() {
		// TODO Auto-generated constructor stub
	}

	public ContaDto(String nome, int agencia, int numero) {
		super();
		this.nome = nome;
		this.agencia = agencia;
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
