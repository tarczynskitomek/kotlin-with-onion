package it.tarczynski.konion.price.domain.policy

import it.tarczynski.konion.price.domain.Price

interface PriceChangePolicy {

    fun isValidPriceChange(oldPrice: Price, newPrice: Price): Boolean
}
