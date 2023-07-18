package br.com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("SELECT t FROM Transferencia t WHERE t.nomeOperadorTransacao LIKE %:nomeOperadorTransacao%")
	List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

	List<Transferencia> findByContaId(Long id);
}
