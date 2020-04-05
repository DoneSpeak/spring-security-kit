OAuth 2.0
===

四种模式
---

### password

简介

流程  

restful使用
```shell
curl http://localhost:8080/auth/token?grant_type=password
```

### client
```shell
curl http://localhost:8080/auth/token?grant_type=client
```

### authentication_code
```shell
curl http://localhost:8080/auth/token?grant_type=authentication_code
```

### refresh_token
```shell
curl http://localhost:8080/auth/token?grant_type=refresh_token
```