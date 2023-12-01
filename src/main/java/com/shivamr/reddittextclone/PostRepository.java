package com.shivamr.reddittextclone;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shivamr.reddittextclone.Post;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PostRepository extends JpaRepository<Post, Integer>, PagingAndSortingRepository<Post, Integer> {
	
	public Optional<Post> findById(Integer id);
	
}