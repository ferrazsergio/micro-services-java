package br.com.microservices.hrworker.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservices.hrworker.entities.Worker;
import br.com.microservices.hrworker.repositories.WorkerRepository;

@Service
public class WorkerService {
	
	private WorkerRepository workerRepository;
	
	@Autowired
	public WorkerService(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}
	
	public List<Worker> getAllWorkers(){
		return workerRepository.findAll();
	}
	
	public Worker findbyId(Long id) {
	    return workerRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado"));
	}
	
}
