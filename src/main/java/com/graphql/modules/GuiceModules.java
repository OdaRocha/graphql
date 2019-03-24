package com.graphql.modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.graphql.datafetcher.AllBooksDataFetcher;
import com.graphql.datafetcher.BookDataFetcher;
import com.graphql.repository.BookRepository;
import com.graphql.service.impl.GraphQLService;

public class GuiceModules extends AbstractModule {

	@Override
	protected void configure() {
		bind(AllBooksDataFetcher.class);
		bind(GraphQLService.class);
		bind(BookDataFetcher.class);
		 install(new ScanningJpaRepositoryModule(BookRepository.class.getPackage().getName(), "mypersistence"));
		Names.bindProperties(binder(), GuiceModules.loadProperties());
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		ClassLoader loader = GuiceModules.class.getClassLoader();
		URL url = loader.getResource("books.graphql");
		try {
			properties.load(url.openStream());
		} catch (FileNotFoundException e) {
			System.out.println("The configuration file books.graphql can not be found");
		} catch (IOException e) {
			System.out.println("I/O Exception during loading configuration");
		}
		return properties;
	}

}
