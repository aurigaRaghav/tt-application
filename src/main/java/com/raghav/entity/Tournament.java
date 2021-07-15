package com.raghav.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tournaments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Tournament Name is required")
	private String name;

	@NotBlank(message = "Start Date is required")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start_date;

	@NotBlank(message = "Registration End Date is required")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registration_end_date;

	private String image;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date created_on;

	private int created_by;
	
	private int set_score;
	
	private String status;
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.ALL
            })
	@JoinTable(name = "tournament_match_types_mapping",
    joinColumns =  @JoinColumn(name = "tournament_id"),
    inverseJoinColumns = @JoinColumn(name = "match_type_id"))
	private List<TournamentMatchTypes> tournamentMatchTypes =  new ArrayList<>();

}
