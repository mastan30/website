package com.project.website.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.website.exceptionHandlers.CustomExceptionHandler;
import com.project.website.model.Posts;
import com.project.website.repositories.PostsRepository;

@Service
public class PostsService {
	
	@Autowired
	private PostsRepository postsRepo;
	
	public List<Posts> getAllPosts(){
		return postsRepo.findAll();
	}
	
	public Optional<Posts> getPostbyId(long id) {
		Optional<Posts> postsOptional = postsRepo.findById(id);
		return postsOptional;
	}
	
	public Posts createPost(Posts post) {
		return postsRepo.save(post);
	}

	public Posts updatePost(Posts post, long id) {
		Optional<Posts> postsOptional = postsRepo.findById(id);
		if (!postsOptional.isPresent())
			throw new CustomExceptionHandler("id-" + id);

		post.setId(id);
		
		return postsRepo.save(post);	
		
	}
	
	public void deletePost(long id) {
		postsRepo.deleteById(id);
	}
	

}
