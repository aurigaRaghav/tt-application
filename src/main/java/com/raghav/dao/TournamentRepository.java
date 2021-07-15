package com.raghav.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.raghav.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {

	
	@Query(value="SELECT * FROM tournaments  WHERE status = :status order by id desc", nativeQuery = true) 
	Page<Tournament> getTournamentsByStatus(@Param("status") String status,Pageable pePageble);
}
