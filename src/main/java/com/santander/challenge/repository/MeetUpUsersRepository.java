package com.santander.challenge.repository;


import com.santander.challenge.model.MeetUpUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guillermovarelli
 */

@Repository
public interface MeetUpUsersRepository extends CrudRepository<MeetUpUsers, Long> {
	
	List<MeetUpUsers> findAll();



}
