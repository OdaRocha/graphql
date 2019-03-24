package com.graphql.datafetcher;

import java.util.List;

import com.google.inject.Inject;
import com.graphql.model.Book;
import com.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class AllBooksDataFetcher implements DataFetcher<List<Book>>{
	
	@Inject
	private BookRepository bookRepository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment environment) {
		return bookRepository.findAll();
	}

}
