package br.com.banco.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Transferencia;
import br.com.banco.entities.dtos.TransferenciaDto;
import br.com.banco.services.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin(origins = "*")
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;

	@GetMapping
	public ResponseEntity<List<TransferenciaDto>> findAll() {
		List<TransferenciaDto> listDto = service.findAll();

		if (listDto.isEmpty())
			return new ResponseEntity<List<TransferenciaDto>>(HttpStatus.NO_CONTENT);

		else
			return new ResponseEntity<List<TransferenciaDto>>(listDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransferenciaDto> findById(@PathVariable Long id) {
		try {
			TransferenciaDto body = new TransferenciaDto(service.findById(id));
			return new ResponseEntity<TransferenciaDto>(body, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<TransferenciaDto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscarContaId/{id}")
	public ResponseEntity<List<Transferencia>> findByContaId(@PathVariable Long id) {
		List<Transferencia> list = service.findByContaId(id);
		Collections.sort(list, Comparator.comparing(Transferencia::getDataTransferencia).reversed());
		
		if (list.isEmpty())
			return new ResponseEntity<List<Transferencia>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Transferencia>>(list, HttpStatus.OK);
	}

	@GetMapping("/buscarNome/{nome}")
	public ResponseEntity<List<Transferencia>> findByNomeOperadorTransacao(@PathVariable String nome) {
		List<Transferencia> list = service.findByNomeOperadorTransacao(nome);
		
		if (list.isEmpty())
			return new ResponseEntity<List<Transferencia>>(list, HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Transferencia>>(list, HttpStatus.OK);
	}

	@GetMapping("/buscarPorData/{dataTransferencia}")
	public ResponseEntity<List<TransferenciaDto>> findByDataTransferencia(@PathVariable String dataTransferencia) {
		List<TransferenciaDto> list = service.findByDataTransferencia(dataTransferencia);
		
		if (list.isEmpty())
			return new ResponseEntity<List<TransferenciaDto>>(list, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<TransferenciaDto>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody TransferenciaDto transferencia) throws Exception {
		service.save(transferencia);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TransferenciaDto transferencia)
			throws Exception {
		service.update(id, transferencia);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
