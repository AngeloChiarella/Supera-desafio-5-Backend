package br.com.banco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;

	public List<Conta> findAll() {
		return repository.findAll();
	}

	public Optional<Conta> findById(Long id) {
		return repository.findById(id);
	}

	public List<Conta> findByNome(String nome) {
		return repository.findByNome(nome);
	}

	public Conta save(Conta conta) {
		return repository.save(conta);
	}

	public Conta update(Long id, Conta conta) {
		Conta newConta = findById(id).get();

		newConta = conta;
		newConta.setId(id);

		return repository.save(newConta);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Conta findByNumero(Integer numero) {
		return repository.findByNumero(numero);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
