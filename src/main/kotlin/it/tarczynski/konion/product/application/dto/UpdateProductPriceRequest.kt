package it.tarczynski.konion.product.application.dto

import it.tarczynski.konion.price.domain.Price
import it.tarczynski.konion.price.domain.toPrice
import java.math.BigDecimal

data class UpdateProductPriceRequest(
    private val priceValue: BigDecimal,
    private val currency: String,
) {

    val newPrice: Price
        get() = priceValue.toPrice(currency)

}
