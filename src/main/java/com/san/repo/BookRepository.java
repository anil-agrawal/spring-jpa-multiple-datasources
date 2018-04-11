package com.san.repo;

import org.springframework.stereotype.Repository;

import com.san.domain.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	public List<Book> findByPublishYear(int publishYear);
}
