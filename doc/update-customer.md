# Update Customer

Update the customer. Can not be changed national id. If the national id is wrong, should be deleted the customer and resave with correct national id.

**URL** : `/customer/{nationalId}`

**URL Parameter** : `nationalId=[String]`

**Method** : `PUT`

**Sample Request**

```json
{
  "firstName": "Ahmet",
  "income": 5000,
  "lastName": "Can",
  "phoneNumber": "05395064707"
}
```
---
## Success Response

**Code** : `200 OK`

```json
{
  "id": 1,
  "firstName": "Ahmet",
  "lastName": "Can",
  "nationalId": "13758028554",
  "income": 5000.0,
  "phoneNumber": "05395064707"
}
````

---

## Error Response

**Code** : `400 BAD REQUEST`

**Response** :

```json
{
  "id": 5,
  "createdDate": "2021-09-27T21:14:58.136Z",
  "lastModifiedDate": "2021-09-27T21:14:58.136Z",
  "status": 400,
  "message": "Customer is not found"
}
```

```json
{
  "id": 1,
  "createdDate": "2021-09-27T21:19:50.128Z",
  "lastModifiedDate": "2021-09-27T21:19:50.128Z",
  "status": 400,
  "message": "Json parse error"
}
```



```json
[
  {
    "id": 2,
    "createdDate": "2021-09-27T21:20:25.084Z",
    "lastModifiedDate": "2021-09-27T21:20:25.084Z",
    "status": 400,
    "field": "phoneNumber",
    "rule": "Invalid phone number"
  },
  {
    "id": 3,
    "createdDate": "2021-09-27T21:20:25.089Z",
    "lastModifiedDate": "2021-09-27T21:20:25.089Z",
    "status": 400,
    "field": "lastName",
    "rule": "Invalid first name or last name"
  },
  {
    "id": 4,
    "createdDate": "2021-09-27T21:20:25.093Z",
    "lastModifiedDate": "2021-09-27T21:20:25.093Z",
    "status": 400,
    "field": "firstName",
    "rule": "Invalid first name or last name"
  }
]
```

**If service down :**
```shell


  Customer service is taking longer than expected. Please try again


```