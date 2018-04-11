package com.san.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.san.h2.domain.Author;
import com.san.h2.repo.AuthorRepository;

@Component
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public void insert10Authors() {
		for (int i = 0; i < 10; i++) {
			authorRepository.save(new Author("author" + i));
		}
	}

}
