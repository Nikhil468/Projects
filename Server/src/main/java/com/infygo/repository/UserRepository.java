package com.infygo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infygo.applicationentity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//@PersistenceContext 
	//EntityManager entityManager;
	
	//public void insertRecordIntoUser(User user);
	
	public ArrayList<User> findByUserId(String userId);
}
