# Insert Data Records to MySQL DB using JPA and Hibernate

- Start the app and then run the *sql scripts

then simply hit the 

````
curl --location --request POST 'http://localhost:8080/masterclass/nameAddress' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Matteo",
    "houseNumber": "3326",
    "city": "Ajax",
    "province": "Ontario",
    "postalCode": "J7G 351"
}'
````

You should be able to see records in MySQL table

````sql
SELECT * FROM camel_masterclass.name_address;
````