package com.graphql.datafetcher;

import com.google.inject.Inject;
import com.graphql.model.Book;
import com.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class BookDataFetcher implements DataFetcher<Book> {
	
	@Inject
	private BookRepository bookRepository;
	
	@Override
	public Book get(DataFetchingEnvironment environment) {
		String id = environment.getArgument("id");
		return  bookRepository.findOne(id);
	}

}
