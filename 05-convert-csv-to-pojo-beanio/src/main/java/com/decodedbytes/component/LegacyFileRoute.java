package com.decodedbytes.component;

import com.decodedbytes.beans.InboundNameAddress;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {
	private static Logger LOG = LoggerFactory.getLogger(LegacyFileRoute.class);
	BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml","inboundMessageStream");

	@Override
	public void configure() throws Exception {

		from("file:src/data/input?fileName=inputFile.csv")
				.routeId("legacyFileMoveRouteId")
				.split(body().tokenize("\n",1, true))
				.unmarshal(inboundDataFormat)
				.process(exchange -> {
					InboundNameAddress inboundNameAddress = exchange.getIn().getBody(InboundNameAddress.class);
					LOG.info(inboundNameAddress.toString());
					exchange.getIn().setBody(inboundNameAddress.toString());
				})
				.to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n")
				.end();
	}
}