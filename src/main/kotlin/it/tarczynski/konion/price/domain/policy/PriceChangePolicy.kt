package it.tarczynski.konion.price.domain.policy

import it.tarczynski.konion.price.domain.Price

interface PriceChangePolicy {

    fun verify(oldPrice: Price, newPrice: Price)
}
