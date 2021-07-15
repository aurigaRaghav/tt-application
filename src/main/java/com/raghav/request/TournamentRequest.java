package com.raghav.request;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;

import com.raghav.entity.TournamentMatchTypes;

public class TournamentRequest {

	private String name;
	private Date start_date;
	private Date registration_end_date;
	private MultipartFile image;
	private Date created_on;
	private int created_by;
	private int set_score;
	private String status;
	
	private List<TournamentMatchTypes> tournamentMatchTypes;
	
	public List<TournamentMatchTypes> getTournamentMatchTypes() {
		return tournamentMatchTypes;
	}
	public void setTournamentMatchTypes(List<TournamentMatchTypes> tournamentMatchTypes) {
		this.tournamentMatchTypes = tournamentMatchTypes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getRegistration_end_date() {
		return registration_end_date;
	}
	public void setRegistration_end_date(Date registration_end_date) {
		this.registration_end_date = registration_end_date;
	}
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public int getSet_score() {
		return set_score;
	}
	public void setSet_score(int set_score) {
		this.set_score = set_score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "TournamentPackage [name=" + name + ", start_date=" + start_date + ", registration_end_date="
				+ registration_end_date + ", image=" + image + ", created_on=" + created_on + ", created_by="
				+ created_by + ", set_score=" + set_score + ", status=" + status + "]";
	}
	
	

}
