package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
@Entity
class Pasta {
	@Id
	private String id; // 고유 식별값
	private String name; // 파스타 이름

	public Pasta(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Pasta(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}

	@RestController
	@RequestMapping("/pasta")
	class RestApiDemoController {
		private final PastaRepository pastaRepository;

		public RestApiDemoController(PastaRepository pastaRepository) {
			this.pastaRepository = pastaRepository;
			this.pastaRepository.saveAll(List.of(
				new Pasta("Tomato"),
				new Pasta("Cream"),
				new Pasta("Oil")
			));
		}


		@GetMapping
		Iterable<Pasta> getPasta() {
			return pastaRepository.findAll();
		}

		@GetMapping("/{id}")
		Optional<Pasta> getPastaById(@PathVariable String id) {
			return pastaRepository.findById(id);
		}

		@PostMapping
		Pasta postPasta(@RequestBody Pasta pasta) {
			return pastaRepository.save(pasta);
		}

		@PutMapping("/{id}")
		ResponseEntity<Pasta> putPasta(@PathVariable String id, 
										@RequestBody Pasta pasta) {
			return (!pastaRepository.existsById(id))  
				? new ResponseEntity<>(pastaRepository.save(pasta), 
					HttpStatus.CREATED)
				: new ResponseEntity<>(pastaRepository.save(pasta), 
				HttpStatus.OK);
		}

		@DeleteMapping("/{id}")
		void deletePasta(@PathVariable String id) {
			pastaRepository.deleteById(id);
		}

	}