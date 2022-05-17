# Client Token With API Gateway

Bu demoda gateway uygulaması ile mikroservislerimizin client token ile güvenliği üzerine bir proje geliştirdik. Dışarıdan gelen herhangi bir isteğin güvenlik kontrolü yapılmadan iç taraftaki mikroservislerimize erişimi engellenir.

## API Kullanımı

Aşağıdaki bütün endpointler için Header kısmında /sign-in endpointinden alınan JWT tokenı Authorization keyi ile birlikte gönderilmesi gereklidir.

#### İşlem kaydetme

Burada veri tabanımıza transaction tablosuna transaction kaydedilir.


```http
  POST /gateway/transaction
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `userId` | `Long` | **Gerekli**. User idsi. |
| `productId` | `Long` | **Gerekli**. Ürün idsi. |

#### İşlem listeleme
Veri tabanındaki bütün transactionları listeler

```http
  GET /gateway/transaction
```

| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |

#### İşlem Silme

Veri tabanında transaction tablosunda kayıt silme işlemi gerçekleştirir.


```http
  DELETE /gateway/transaction/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :-------------------------------- |