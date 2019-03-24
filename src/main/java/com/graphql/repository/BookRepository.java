package com.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphql.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, String>{

}
