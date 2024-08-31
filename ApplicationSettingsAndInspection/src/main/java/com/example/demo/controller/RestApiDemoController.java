package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pasta;
import com.example.demo.repository.PastaRepository;

import jakarta.annotation.PostConstruct;

@Component
class DataLoader {
	private final PastaRepository pastaRepository;
	public DataLoader(PastaRepository pastaRepository) {
		this.pastaRepository = pastaRepository;
	}

	@PostConstruct
	private void loadDate() {
		pastaRepository.saveAll(List.of(
				new Pasta("Tomato"),
				new Pasta("Cream"),
				new Pasta("Oil")
			));
	}
}

@RestController
@RequestMapping("/pasta")
public class RestApiDemoController {
    	private final PastaRepository pastaRepository;

        public RestApiDemoController(PastaRepository pastaRepository) {
			this.pastaRepository = pastaRepository;
			pastaRepository.saveAll(List.of(
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

			return (pastaRepository.existsById(id)) 
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

