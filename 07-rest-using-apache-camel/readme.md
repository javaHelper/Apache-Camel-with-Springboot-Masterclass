#

```
curl --location --request POST 'http://localhost:8080/masterclass/nameAddress' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Matteo",
    "houseNumber": "3326",
    "city": "Ajax",
    "province": "Ontario",
    "postalCode": "J7G 351"
}'
```