package br.com.microservices.hrworker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.hrworker.dto.WorkerDTO;
import br.com.microservices.hrworker.entities.Worker;
import br.com.microservices.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

	private WorkerService workerService;
	private Environment env;
	private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
	
	@Autowired
	public WorkerController(WorkerService workerService, Environment env) {
		super();
		this.workerService = workerService;
		this.env = env;
	}

	@GetMapping
	public ResponseEntity<List<WorkerDTO>> getAll() {
		try {
			
			List<Worker> listWorkers = workerService.getAllWorkers();
			List<WorkerDTO> listWorkerDTOs = listWorkers.stream().map(WorkerDTO::new).collect(Collectors.toList());
			if (listWorkers.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(listWorkerDTOs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}



	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {
		try {
			logger.info("PORT = " + env.getProperty("local.server.port"));
			Worker worker = workerService.findbyId(id);
			return ResponseEntity.ok().body(new WorkerDTO(worker));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
