package com.santander.challenge.repository;

import com.santander.challenge.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guillermovarelli
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();

	@Query("SELECT u FROM User u where u.userName = ?1")
	User findByUserName(String userName);

}
