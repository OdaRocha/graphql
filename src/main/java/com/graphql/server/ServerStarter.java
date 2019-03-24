package com.graphql.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.graphql.controller.ServerController;
import com.graphql.modules.GuiceModules;

import spark.Spark;

public class ServerStarter {

	public static void main(String[] args) {
		Spark.port(8092);
		Spark.threadPool(1000);
		Injector injector = Guice.createInjector(new GuiceModules());
		ServerController controller = injector.getInstance(ServerController.class);
		controller.router();
	}

}
