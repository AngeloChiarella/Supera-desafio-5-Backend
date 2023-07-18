package br.com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	@Query("SELECT c FROM Conta c WHERE c.nomeResponsavel LIKE %:nome%")
	List<Conta> findByNome(String nome);

	Conta findByNumero(Integer numero);
	

}
