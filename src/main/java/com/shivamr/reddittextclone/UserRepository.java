package com.shivamr.reddittextclone;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shivamr.reddittextclone.UserE;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<UserE, Integer> {
	public Optional<UserE> findByuserName(String username);
}