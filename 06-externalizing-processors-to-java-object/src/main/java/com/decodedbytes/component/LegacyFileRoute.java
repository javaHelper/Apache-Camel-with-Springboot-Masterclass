package com.decodedbytes.component;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.decodedbytes.processor.InboundMessageProcessor;

@Component
public class LegacyFileRoute extends RouteBuilder {
	private static Logger LOG = LoggerFactory.getLogger(LegacyFileRoute.class);
	BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml",
			"inboundMessageStream");

	@Override
	public void configure() throws Exception {

		from("file:src/data/input?fileName=inputFile.csv&noop=true")
				.routeId("legacyFileRouteId")
				.split(body().tokenize("\n", 1, true))
				.streaming()
				.unmarshal(inboundDataFormat)
					.process(new InboundMessageProcessor())
					.log(LoggingLevel.INFO, "Transform Body: ${body}")
					.convertBodyTo(String.class)
				.to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n")
				.end();
	}
}