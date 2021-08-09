# MEMBER

### Member Sign `/auth/sign`

- Request Header

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

<hr />

### Member Login '/auth/loginProc'

```
{
    "email": "wrjs@naver.com",
    "password" : "!Computer123"
}
```