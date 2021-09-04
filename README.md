# MEMBER

### Member Sign

- URL
    + `http://localhost:8080/auth/sign`

- Method
    + `POST`

```json
{
    "Content-Type": "application/json"
}
```

- Request Body

```json
{
    "email" : "wrjs@naver.com",
    "password" : "!Computer123",
    "name" : "김동건",
    "nickname" : "외쳐갓동건",
    "phoneNumber" : "01031829709"
}
```

- Response Body

```json
{
    "status": 200,
    "message": "회원가입 성공",
    "data": null
}
```

<hr />

### Member Login '/auth/loginProc'

```json
{
    "email": "wrjs@naver.com",
    "password" : "!Computer123"
}
```

```sql
INSERT INTO AUTHORITY (AUTHORITY_NAME)
values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME)
values ('ROLE_ADMIN');
```