package it.tarczynski.konion.product.domain

import it.tarczynski.konion.price.domain.Price
import it.tarczynski.konion.price.domain.policy.PriceChangePolicy
import it.tarczynski.konion.product.domain.ProductStatus.ACTIVE
import it.tarczynski.konion.product.domain.ProductStatus.NEW
import it.tarczynski.konion.product.domain.ProductStatus.WITHDRAWN
import java.lang.IllegalArgumentException
import java.util.*

enum class ProductStatus {
    NEW, ACTIVE, WITHDRAWN
}

@JvmInline
value class ProductId(val value: UUID) {

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
        if (!priceChangePolicy.isValidPriceChange(price, newPrice)) {
            throw IllegalArgumentException("Invalid price change from: [$price] to [$newPrice]")
        }
        return copy(price = newPrice)
    }

    fun withdraw(): Product {
        if (status == NEW) {
            throw IllegalStateException("Cannot withdraw new product")
        }
        return copy(status = WITHDRAWN)
    }

    fun activate(activationPrice: Price): Product {
        if (activationPrice.isZero()) {
            throw IllegalArgumentException("Activation price cannot be zero")
        }
        return copy(status = ACTIVE, price = activationPrice)
    }

    fun isAvailableForSale(): Boolean {
        return status == ACTIVE && price > Price.zero(price.currency)
    }

}
