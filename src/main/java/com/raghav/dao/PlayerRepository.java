package com.raghav.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.raghav.entity.Player;
import com.raghav.entity.Tournament;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long>{

//	@Query(value="SELECT * FROM players order by id desc", nativeQuery = true) 
//	Page<Tournament> getPlayers(@Param("status") String status,Pageable pePageble);

}
