package it.tarczynski.konion.product.application.dto

import it.tarczynski.konion.price.domain.Price
import java.math.BigDecimal

data class UpdateProductPriceRequest(
    private val priceValue: BigDecimal,
    private val currency: String,
) {

    val newPrice: Price
        get() = Price(
            value = priceValue,
            currency = currency,
        )
}
