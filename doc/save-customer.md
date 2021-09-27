# Save Customer

Save a new customer

**URL** : `/customer`

**Method** : `POST`

**Sample Request**

```json
{
  "firstName": "Emre",
  "income": 1000,
  "lastName": "Dalcı",
  "nationalId": 13758028554,
  "phoneNumber": "05395064707"
}
```

---
## Success Response

**Code** : `200 OK`

```json
{
  "id": 1,
  "firstName": "Emre",
  "lastName": "Dalcı",
  "nationalId": "13758028554",
  "income": 1000.0,
  "phoneNumber": "05395064707"
}
````

---
## Error Response

**Code** : `400 BAD REQUEST`

**Response** :

```json

{
  "id": 1,
  "createdDate": "2021-09-27T20:52:59.037Z",
  "lastModifiedDate": "2021-09-27T20:52:59.037Z",
  "status": 400,
  "field": "firstName",
  "rule": "Invalid first name or last name"
}

```

```json

{
  "id": 1,
  "createdDate": "2021-09-27T20:53:54.720Z",
  "lastModifiedDate": "2021-09-27T20:53:54.720Z",
  "status": 400,
  "message": "Customer is already exist"
}

```

```json

{
  "id": 2,
  "createdDate": "2021-09-27T20:54:22.526Z",
  "lastModifiedDate": "2021-09-27T20:54:22.526Z",
  "status": 400,
  "message": "Json parse error"
}

```

**If service down :**
```shell


  Customer service is taking longer than expected. Please try again


```
