package br.com.microservices.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.microservices.hrpayroll.dto.WorkerDTO;


@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignClient {
	@GetMapping(value = "/{id}")	
	ResponseEntity<WorkerDTO> findById(@PathVariable Long id);

}
