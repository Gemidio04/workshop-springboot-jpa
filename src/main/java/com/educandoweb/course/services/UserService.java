package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll(); 
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id); 
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
	}
	
	public User insert(User obj) {
		return repository.save(obj); 
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException(id); 
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		User entity = repository.getReferenceById(id); 
		updateData(entity, user); 
		return repository.save(entity); 
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
