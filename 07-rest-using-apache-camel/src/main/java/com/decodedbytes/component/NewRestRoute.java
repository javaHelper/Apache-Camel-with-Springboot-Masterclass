package com.decodedbytes.component;

import com.decodedbytes.beans.InboundNameAddress;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class NewRestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
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
                .to("direct:process");

        from("direct:process").routeId("processMessageRouteId")
                .process(exchange -> {}) //>>> replace exchange lambda function with your processor class here
                .log(LoggingLevel.INFO, "Transformed Body: ${body}")
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n");
    }
}