package com.decodedbytes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
public class LegacyFileRouteTest {

    @Autowired
    private CamelContext context;

    @EndpointInject("mock:result")
    private MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testFileFlow() throws Exception {
        String expectedBody = "This is the input file";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);  // Fixed this

        AdviceWith.adviceWith(context, "legacyFileMoveRouteId", routeBuilder -> {
            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });

        context.start();
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testByMockingFromEndpoint() throws Exception {

        String expectedBody = "This is the new input file";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        AdviceWith.adviceWith(context, "legacyFileRouteId", routeBuilder -> {
            routeBuilder.replaceFromWith("direct:mockStart");
            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });

        context.start();
        producerTemplate.sendBody("direct:mockStart", "This is the new input file");
        mockEndpoint.assertIsSatisfied();


    }
}