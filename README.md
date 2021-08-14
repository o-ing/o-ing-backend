# MEMBER

### Member Sign 

- URL
    + `http://localhost:8080/auth/sign`

- Method
    + `POST`

```
{
    "Content-Type": "application/json"
}
```

- Request Body

```
{
    "email": "wrjs@naver.com",
    "password" : "!Computer123",
    "name" : "김동건",
    "nickname" : "외쳐갓동건",
    "phoneNumber" : "01031829709"
}
```

- Response Body

```
{
    "status": 200,
    "message": "회원가입 성공",
    "data": null
}
```

<hr />

### Member Login '/auth/loginProc'

```
{
    "email": "wrjs@naver.com",
    "password" : "!Computer123"
}
```