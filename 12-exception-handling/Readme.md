#

Please make sure you've stop the ActiveMQ and then hit the below endpoint

```
curl --location --request POST 'http://localhost:8080/masterclass/nameAddress' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Matt",
    "houseNumber": "333",
    "city": "Reston",
    "province": "USA",
    "postalCode": "J7J 231"
}'
```


```
2023-01-17 11:15:10.350  INFO 7338 --- [           main] com.decodedbytes.Application             : Started Application in 6.944 seconds (JVM running for 7.67)
2023-01-17 11:15:15.368 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=0, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
2023-01-17 11:15:15.609  INFO 7338 --- [qtp833096840-35] processMessageRouteId                    : simple{InboundNameAddress(id=null, name=Matt, houseNumber=333, city=Reston, province=USA, postalCode=J7J 231)}
2023-01-17 11:15:15.610  INFO 7338 --- [qtp833096840-35] processMessageRouteId                    : ## Sending to DB EP
2023-01-17 11:15:15.610  INFO 7338 --- [qtp833096840-35] toDBId                                   : ### Sending to ActiveMQ EP
2023-01-17 11:15:15.913  INFO 7338 --- [qtp833096840-35] toActiveMQId                             : >>> in ActiveMQ EP
2023-01-17 11:15:15.946  INFO 7338 --- [qtp833096840-35] toActiveMQId                             : JMS Exception has occurred; handling gracefully
2023-01-17 11:15:20.385 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=1, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
2023-01-17 11:15:25.392 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=2, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
2023-01-17 11:15:30.411 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=3, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
2023-01-17 11:15:35.430 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=4, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
2023-01-17 11:15:40.446 ERROR 7338 --- [meaddressqueue]] c.c.j.DefaultJmsMessageListenerContainer : Could not refresh JMS Connection for destination 'nameaddressqueue' - retrying using FixedBackOff{interval=5000, currentAttempts=5, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused (Connection refused)
```