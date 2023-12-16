### Memo
복잡한 Join SQL 보다는 개별로 호출하여 소스 단위에서 합친다.
* Controller `/apis/carts/items`
```kotlin
val cart = cartService.getCartByCustomerId(request.customerId)
val cartMenus = cartItemService.getItemsByCartId(cart.id)
val cartMenusDTO = cartMenus.map { CartMenuDTO.from(it) }

CartQueryResponse(
    customerId = request.customerId,
    menus = cartMenusDTO,
)
```

### TODO
글로벌 예외 처리 (컨트롤러)

### 의문점
패키지 구조에 대해서 ?
