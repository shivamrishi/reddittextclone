package com.shivamr.reddittextclone;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.shivamr.reddittextclone.Interaction;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InteractionRepository extends JpaRepository<Interaction, Integer> {
	
	
	
}