package br.com.banco.entities.dtos;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.banco.entities.Transferencia;

public class TransferenciaDto {

	@JsonIgnore
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private LocalDateTime dataTransferencia;
	
	private String valor;
	private String tipo;
	private String nomeOperadorTransacao;
	private Long contaId;
	private String saldo;

	public TransferenciaDto(Optional<Transferencia> transferencia) {
		this.id = transferencia.get().getId();
		this.dataTransferencia = transferencia.get().getDataTransferencia();
		this.valor = transferencia.get().getValor().toString();
		this.tipo = transferencia.get().getTipo();
		this.nomeOperadorTransacao = transferencia.get().getNomeOperadorTransacao();
		this.contaId = transferencia.get().getConta().getId();
		
	}

	public TransferenciaDto() {
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeOperadorTransacao() {
		return nomeOperadorTransacao;
	}

	public void setNomeOperadorTransacao(String nomeOperadorTransacao) {
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

}
