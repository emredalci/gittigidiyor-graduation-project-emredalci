# Credit Result

Get the customer's all credit results

**URL** : `/result/{nationalId}`

**URL Parameter** : `nationalId=[String]`

**Method** : `GET`

---
## Success Response

**Code** : `200 OK`

**Can be lots of credit application**

**If the customer does not have a credit result, the response will be returned an empty list**
```json
[
  {
    "status": "ACCEPT",
    "creditLimit": 4000.0,
    "customerNationalId": "13758028554"
  }
]
````
---
