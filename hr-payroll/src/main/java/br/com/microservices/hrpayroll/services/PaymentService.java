package br.com.microservices.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservices.hrpayroll.dto.WorkerDTO;
import br.com.microservices.hrpayroll.entities.Payment;
import br.com.microservices.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(long workerId, int days) {
		
		WorkerDTO workerDto = workerFeignClient.findById(workerId).getBody();
		
		if (workerDto == null) {
			throw new NullPointerException("Worker not found for id: " + workerId);
		}
		return new Payment(workerDto.getName(), workerDto.getDailyIncome(), days);
	}
}
