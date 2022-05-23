# Client Token With API Gateway

Bu demoda gateway uygulaması ile mikroservislerin client token ile güvenliği 
Dışarıdan gelen herhangi bir isteğin güvenlik kontrolü yapılmadan iç taraftaki mikroservislerine erişimi engellenir.

## API Kullanımı

Aşağıdaki bütün endpointler için Header kısmında /sign-in endpointinden alınan JWT tokenı Authorization keyi ile birlikte gönderilmesi gereklidir.

#### Ürün kaydetme

Burada veri tabanımıza product tablosuna product kaydedilir.


```http
  POST /gateway/product
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Gerekli**. Ürün adı. |
| `price` | `double` | **Gerekli**. Ürün fiyatı. |

#### Ürünleri listele
Veri tabanındaki bütün ürünleri listeler

```http
  GET /gateway/product
```

| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |

#### Ürün Silme

Veri tabanında product tablosunda kayıt silme işlemi gerçekleştirir.


```http
  DELETE /gateway/product/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :-------------------------------- |
