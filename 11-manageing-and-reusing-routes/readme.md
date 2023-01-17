#

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