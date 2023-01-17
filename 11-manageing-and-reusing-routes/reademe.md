#

````
// This works parelly - send to DB and sending to ActiveMQ parallel
.multicast()
    .to("jpa:"+InboundNameAddress.class.getName())
    .to("activemq:queue:nameaddressqueue?exchangePattern=InOnly");
````