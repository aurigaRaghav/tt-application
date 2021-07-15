package com.raghav.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghav.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	
		
//	@Query(value = "UPDATE `ttapplication`.`users` SET `one_time_password`= :otp WHERE `id`= :email", nativeQuery = true)
//	void updateUserByEmail(@Param("email") String email, 
//	  @Param("otp") int otp);
//	
	@Query("SELECT u FROM User u WHERE u.email = :email") 
	User updateUserByEmail(@Param("email") String email);
	
//	//fetch user with by role
//	@Query("select * from users left join user_roles_mapping on users.id = user_roles_mapping.user_id left join roles on roles.id = user_roles_mapping.role_id where roles.name = role;") 
//	List<User> getAllPlayers(@Param("role_id") int role_id);
}
