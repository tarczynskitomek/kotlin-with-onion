package it.tarczynski.konion.product.domain

import it.tarczynski.konion.price.domain.Price
import it.tarczynski.konion.price.domain.policy.PriceChangePolicy
import it.tarczynski.konion.product.domain.ProductStatus.ACTIVE
import it.tarczynski.konion.product.domain.ProductStatus.NEW
import it.tarczynski.konion.product.domain.ProductStatus.WITHDRAWN
import java.util.*

enum class ProductStatus {
    NEW, ACTIVE, WITHDRAWN
}

inline class ProductId(val value: UUID) {

    companion object {

        fun next(): ProductId {
            return ProductId(UUID.randomUUID())
        }
    }
}

fun String.toProductId(): ProductId {
    return ProductId(UUID.fromString(this))
}

data class Product(
    val id: ProductId,
    val price: Price,
    val name: String,
    val status: ProductStatus,
) {

    fun updatePrice(newPrice: Price, priceChangePolicy: PriceChangePolicy): Product {
        priceChangePolicy.verify(price, newPrice)
        return copy(price = newPrice)
    }

    fun withdraw(): Product {
        if (status == NEW) {
            throw IllegalStateException("Cannot withdraw new product")
        }
        return copy(status = WITHDRAWN)
    }

    fun isActive(): Boolean {
        return status == ACTIVE && price > Price.zero(price.currency)
    }

    fun activate(): Product {
        return copy(status = ACTIVE)
    }

}
