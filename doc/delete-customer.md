# Delete Customer

Delete the customer

**URL** : `/customer/{nationalId}`

**URL Parameter** : `nationalId=[String]`

**Method** : `DELETE`

---

## Success Response

**Code** : `200 OK`

**Response** :

```json
{
  "id": 1,
  "firstName": "Emre",
  "lastName": "Dalcı",
  "nationalId": "13758028554",
  "income": 1000.0,
  "phoneNumber": "05395064707"
}
```

---

## Error Response

**Code** : `400 BAD REQUEST`

**Response** :



```json
{
  "id": 1,
  "createdDate": "2021-09-27T21:07:59.128Z",
  "lastModifiedDate": "2021-09-27T21:07:59.128Z",
  "status": 400,
  "message": "Customer is not found"
}
```

**If service down :**
```shell


  Customer service is taking longer than expected. Please try again


```