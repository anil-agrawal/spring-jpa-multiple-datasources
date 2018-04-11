package com.san.h2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.san.h2.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}