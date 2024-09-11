package br.com.microservices.hrworker.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.hrworker.entities.Worker;
import br.com.microservices.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

	private WorkerService workerService;

	@Autowired
	public WorkerController(WorkerService workerService) {
		super();
		this.workerService = workerService;
	}

	@GetMapping
	public ResponseEntity<List<Worker>> getAll() {
		try {
			List<Worker> listWorkers = workerService.getAllWorkers();

			if (listWorkers.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(listWorkers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		try {
			Worker worker = workerService.findbyId(id);
			return ResponseEntity.ok(worker);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
