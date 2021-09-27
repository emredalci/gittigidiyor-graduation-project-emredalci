# Apply For Loan

Apply for loan with customer nationalId

**URL** : `/customer/{nationalId}`

**URL Parameter** : `nationalId=[String]`

**Method** : `GET`

---

## Success Response

**Code** : `200 OK`

```json
{
  CreditResult{status=ACCEPT, creditLimit=4000.0, customerNationalId='13758028554'}
}
```

## Error Response

**Code** : `400 BAD REQUEST`

**Response** :

```json
{
  "id": 1,
  "createdDate": "2021-09-27T21:23:00.267Z",
  "lastModifiedDate": "2021-09-27T21:23:00.267Z",
  "status": 400,
  "message": "Customer is not found"
}
```

**If customer-service is down :**
```shell


  Customer service is taking longer than expected. Please try again


```

```json
{
  "id": 1,
  "createdDate": "2021-09-27T21:55:02.442Z",
  "lastModifiedDate": "2021-09-27T21:55:02.442Z",
  "status": 400,
  "message": "No instances available for NOTIFICATION-SERVICE"
}
```

**If notification-service is down**
```shell


  Notification service is taking longer than expected. Please try again


```




