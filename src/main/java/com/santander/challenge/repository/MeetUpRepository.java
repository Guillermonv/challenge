package com.santander.challenge.repository;

import com.santander.challenge.model.MeetUp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guillermovarelli
 */

@Repository
public interface MeetUpRepository extends CrudRepository<MeetUp, Long> {
	
	List<MeetUp> findAll();

}
