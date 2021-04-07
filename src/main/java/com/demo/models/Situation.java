package com.demo.models;

public enum Situation {
	emAndamento("Em Andamento"),
	Concluída("Concluída");
	
	private String label;
	
	private Situation(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
