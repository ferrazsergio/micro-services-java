package br.com.microservices.hrpayroll.dto;

import java.io.Serializable;

import br.com.microservices.hrpayroll.entities.Worker;

public class WorkerDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Double dailyIncome;
	
	public WorkerDTO() {
	}

	public WorkerDTO(Worker obj) {
		this.name = obj.getName();
		this.dailyIncome = obj.getDailyIncome();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}
	
	// Método para converter DTO para a entidade Worker
    public Worker toEntity() {
        return new Worker(this.name, this.dailyIncome);
    }
}
