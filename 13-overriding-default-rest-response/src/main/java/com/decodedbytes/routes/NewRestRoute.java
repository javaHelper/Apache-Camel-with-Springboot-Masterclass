package com.decodedbytes.routes;

import java.net.ConnectException;

import javax.jms.JMSException;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.decodedbytes.beans.InboundNameAddress;

@Component
public class NewRestRoute extends RouteBuilder {
    @SuppressWarnings("unchecked")
	@Override
    public void configure() throws Exception {

        onException(JMSException.class, ConnectException.class)
                .routeId("jmsExceptionRouteId")
                .handled(true)
                .log(LoggingLevel.INFO, "JMS Exception has occurred; handling gracefully");

        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        rest("masterclass")
                .id("restRouter")
                .produces("application/json")
                .post("nameAddress").type(InboundNameAddress.class) //>>> replace with your POJO here
                .to("direct:process")
        ;

        from("direct:process")
                .routeId("processMessageRouteId")
                .process(exchange -> {}) //>>> replace exchange lambda function with your processor class here
                .log(LoggingLevel.INFO, String.valueOf(simple("${body}")))
                .log(LoggingLevel.INFO, "## Sending to DB EP")
                .to("direct:toDB")
                .to("direct:toActiveMQ")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .transform().simple("Message Processed and result generated with Body: ${body}")
                .end();

        from("direct:toDB")
                .routeId("toDBId")
                .log(LoggingLevel.INFO, "### Sending to ActiveMQ EP")
                .to("jpa:"+InboundNameAddress.class.getName());

        from("direct:toActiveMQ")
                .routeId("toActiveMQId")
                .log(LoggingLevel.INFO, ">>> in ActiveMQ EP")
                .to("activemq:queue:nameaddressqueue?exchangePattern=InOnly");
    }
}