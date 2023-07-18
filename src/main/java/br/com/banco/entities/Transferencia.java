package br.com.banco.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.banco.entities.dtos.TransferenciaDto;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data_transferencia")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private LocalDateTime dataTransferencia;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "nome_operador_transacao")
	private String nomeOperadorTransacao;

	@ManyToOne
	@JoinColumn(name = "conta_id")
	@JsonIgnore
	private Conta conta;
	
	private Double saldo;

	public Transferencia() {
		// TODO Auto-generated constructor stub
	}

	public Transferencia(LocalDateTime dataTransferencia, Double valor, String tipo, String nomeOperadorTransacao,
			Conta conta) {
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
		this.saldo = conta.getSaldo();
	}

	public Transferencia(TransferenciaDto transferenciaDto, Conta conta2) {
		this.dataTransferencia = transferenciaDto.getDataTransferencia();
		this.valor = Double.parseDouble(transferenciaDto.getValor());
		this.tipo = transferenciaDto.getTipo();
		this.nomeOperadorTransacao = transferenciaDto.getNomeOperadorTransacao();
		this.conta = conta2;
		this.saldo = conta.getSaldo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDateTime dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getNomeOperadorTransacao() {
		return nomeOperadorTransacao;
	}

	public void setNomeOperadorTransacao(String nomeOperadorTransacao) {
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
