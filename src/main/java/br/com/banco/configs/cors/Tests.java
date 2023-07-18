package br.com.banco.configs.cors;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;

@Configuration
@SpringBootConfiguration
public class Tests implements CommandLineRunner {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public void run(String... args) throws Exception {
//		contaRepository.deleteAll();
		Conta c1 = new Conta("Angelo");
		c1.setNumero(123456);
		c1.setAgencia(4321);
		c1.setSaldo(100000.00);
		
		contaRepository.saveAll(Arrays.asList(c1));
	}

}