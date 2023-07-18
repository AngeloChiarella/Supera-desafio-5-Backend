package br.com.banco.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.entities.dtos.TransferenciaDto;
import br.com.banco.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;

	@Autowired
	private ContaService contaService;

	public Optional<Transferencia> findById(Long id) {
		return repository.findById(id);
	}

	public List<TransferenciaDto> findAll() {
		List<Transferencia> transferencias = repository.findAll();
		List<TransferenciaDto> transferenciasDto = new ArrayList<>();

		for (Transferencia transferencia : transferencias) {
			transferenciasDto.add(dtoConverter(transferencia));
		}

		transferenciasDto.sort(Comparator.comparing(t -> t.getDataTransferencia()));
		return transferenciasDto;
	}

	public List<Transferencia> findByNomeOperadorTransacao(String nome) {
		return repository.findByNomeOperadorTransacao(nome);
	}

	public List<Transferencia> findByContaId(Long id) {
		return repository.findByContaId(id);
	}

	public List<TransferenciaDto> findByDataTransferencia(String dataTransferencia) {
		List<TransferenciaDto> newListDto = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		findAll().stream()
				.filter(dto -> dto.getDataTransferencia().equals(LocalDateTime.parse(dataTransferencia, formatter)))
				.forEach(newListDto::add);
		if (newListDto.isEmpty())
			return newListDto;
		return null;
	}

	public void save(TransferenciaDto transferenciaDto) throws Exception {
		Optional<Conta> conta = contaService.findById(transferenciaDto.getContaId());
		Transferencia novaTransferencia = new Transferencia(transferenciaDto, conta.get());

		if (validacoesTransferencias(conta.get().getSaldo(), transferenciaDto)) {
			if (transferenciaDto.getTipo() == "SAQUE")
				novaTransferencia.setNomeOperadorTransacao(conta.get().getNomeResponsavel());
			repository.save(novaTransferencia);
		}
	}

	public void update(Long id, TransferenciaDto transferenciaDto) throws Exception {
		Optional<Conta> conta = contaService.findById(transferenciaDto.getContaId());
		Transferencia novaTransferencia = new Transferencia(transferenciaDto, conta.get());

		if (repository.existsById(id) && (validacoesTransferencias(conta.get().getSaldo(), transferenciaDto))) {
			novaTransferencia.setId(id);
			repository.save(novaTransferencia);
		}

	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean validacoesTransferencias(Double saldo, TransferenciaDto transferencia) throws Exception {
		Double valor = Double.parseDouble(transferencia.getValor());
		LocalDateTime now = LocalDateTime.now();

		if (transferencia.getDataTransferencia().isBefore(now))
			throw new Exception("Essa transferencia so sera possivel se tiver uma maquina do tempo!");

		if ((transferencia.getTipo() == "SAQUE" || transferencia.getTipo() == "TRANSFERENCIA") && valor < saldo)
			throw new Exception("Voce precisa de um emprestimo ou cheque especial, consulte seu gerente!");

		if (transferencia.getTipo() != "DEPOSITO" && valor < 0)
			return false;

		return true;
	}

	private TransferenciaDto dtoConverter(Transferencia transferencia) {
		TransferenciaDto DTO = new TransferenciaDto();

		DTO.setId(transferencia.getId());
		DTO.setDataTransferencia(transferencia.getDataTransferencia());
		DTO.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
		DTO.setTipo(transferencia.getTipo());
		DTO.setValor(transferencia.getValor().toString());
		DTO.setContaId(transferencia.getConta().getId());

		return DTO;
	}

}
