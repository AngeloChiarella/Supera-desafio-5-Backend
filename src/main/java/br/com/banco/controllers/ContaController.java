package br.com.banco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*")
public class ContaController {

	@Autowired
	private ContaService service;

	@GetMapping
	public ResponseEntity<List<Conta>> findAll() {
		return new ResponseEntity<List<Conta>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Conta>> findById(@PathVariable Long id) {
		Optional<Conta> conta = service.findById(id);
		
		if (conta.isEmpty())
			return new ResponseEntity<Optional<Conta>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Optional<Conta>>(conta, HttpStatus.OK);
	}

	@GetMapping("/buscarConta/{numero}")
	public ResponseEntity<Conta> findByNumero(@PathVariable Integer numero) {
		try {
			return new ResponseEntity<Conta>(service.findByNumero(numero), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscarNome/{nome}")
	public ResponseEntity<List<Conta>> findByNome(@PathVariable String nome) {
		List<Conta> list = service.findByNome(nome);
		
		if (list.isEmpty())
			return new ResponseEntity<List<Conta>>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Conta>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Conta> insert(@RequestBody Conta conta) throws Exception {
		return new ResponseEntity<Conta>(service.save(conta), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Conta> update(@PathVariable @Validated Long id, @RequestBody Conta conta) throws Exception {
		return new ResponseEntity<Conta>(service.update(id, conta), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
