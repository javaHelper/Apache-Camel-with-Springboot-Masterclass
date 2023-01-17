package com.decodedbytes.component;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {
	private static Logger LOG = LoggerFactory.getLogger(LegacyFileRoute.class);

	@Override
	public void configure() throws Exception {

		from("file:src/data/input?fileName=inputFile.csv")
				.routeId("legacyFileMoveRouteId")
				.split(body().tokenize("\n",1, true))
				.process(exchange -> {
					String body = exchange.getIn().getBody(String.class);
					LOG.info("Data :: "+ body);
				})
				.to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\n")
				.end();
	}
}