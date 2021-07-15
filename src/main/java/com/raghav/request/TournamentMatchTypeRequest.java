package com.raghav.request;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.raghav.entity.Tournament;

public class TournamentMatchTypeRequest {

	
	private String name;
	
	private List<Tournament> tournaments;
	
	public List<Tournament> getTournaments() {
		return tournaments;
	}
	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TournamentMatchTypeRequest [name=" + name + "]";
	}
	
	
	
	
	
	
}
