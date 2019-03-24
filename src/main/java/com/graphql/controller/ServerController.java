package com.graphql.controller;

import static spark.Spark.path;
import static spark.Spark.post;

import com.google.inject.Inject;
import com.graphql.service.impl.GraphQLService;

public class ServerController {

	@Inject
	GraphQLService graphQLService;

	public void router() {

		path("/rest", () -> {
			post("/books", (request, response) -> {
				return graphQLService.getGraphQL().execute(request.body());
			});
		});
	}

}
