package it.tarczynski.konion.product.application.dto

import it.tarczynski.konion.product.domain.Product
import it.tarczynski.konion.product.domain.ProductStatus
import java.math.BigDecimal
import java.util.*

class ProductResponse(
    val id: UUID,
    val price: BigDecimal,
    val currency: String,
    val status: ProductStatus
) {

    companion object {

        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id.value,
                price = product.price.value,
                currency = product.price.currency,
                status = product.status
            )
        }
    }
}
