# Client Token With API Gateway

Bu demoda gateway uygulaması ile mikroservislerimizin client token ile güvenliği üzerine bir proje geliştirdik. Dışarıdan gelen herhangi bir isteğin güvenlik kontrolü yapılmadan iç taraftaki mikroservislerimize erişimi engellenir.

## API Kullanımı

Bu iki endpointte herhangi bir Authorization keyi göndermeye gerek yok.

#### Kullanıcı kayıt

Burada veri tabanımıza user tablosuna user kaydedilir.


```http
  POST /api/authentication/sign-up
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Gerekli**. Giriş yapılırken kullanılacak olan kullanıcı adı. |
| `password` | `string` | **Gerekli**. Kullanıcı şifresi. |
| `name` | `string` | **Gerekli**. Kullanıcı adı. |

#### Kullanıcı giriş
Veri tabanındaki bütün transactionları listeler

```http
  POST /api/authentication/sign-in
```

| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |
| `username` | `string` | **Gerekli**. Kullanıcı adı. |
| `password` | `string` | **Gerekli**. Kullanıcı şifresi. |