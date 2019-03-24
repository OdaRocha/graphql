package com.graphql.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.graphql.datafetcher.AllBooksDataFetcher;
import com.graphql.datafetcher.BookDataFetcher;
import com.graphql.model.Book;
import com.graphql.repository.BookRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import spark.resource.Resource;

public class GraphQLService {

	@Named("classpath:books.graphql")
	Resource resource;

	@Inject
	private BookRepository bookRepository;
	
	@Inject
	private AllBooksDataFetcher allBooksDataFetcher;
	
	@Inject
	private BookDataFetcher bookDataFetcher;

	private GraphQL graphQL;

	public void loadSchema() throws IOException {

		// Load Books into the Book Repository
		loadDataIntoHSQL();

		// get the schema
		File schemaFile = resource.getFile();
		// parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();

	}

	private void loadDataIntoHSQL() {

		Stream.of(new Book("123", "Book of Clouds", "Kindle Edition", new String[] { "Chloe Aridjis" }, "Nov 2017"),
				new Book("124", "Cloud Arch & Engineering", "Orielly", new String[] { "Peter", "Sam" }, "Jan 2015"),
				new Book("125", "Java 9 Programming", "Orielly", new String[] { "Venkat", "Ram" }, "Dec 2016"))
				.forEach(book -> {
					bookRepository.save(book);
				});
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher).dataFetcher("book", bookDataFetcher)).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
