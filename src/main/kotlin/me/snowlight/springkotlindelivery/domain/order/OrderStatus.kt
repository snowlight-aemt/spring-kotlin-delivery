package me.snowlight.springkotlindelivery.domain.order

enum class OrderStatus(description: String) {
    READY("대기중"),
    CANCEL("취소"),
    COMPLETE("완료")
}
