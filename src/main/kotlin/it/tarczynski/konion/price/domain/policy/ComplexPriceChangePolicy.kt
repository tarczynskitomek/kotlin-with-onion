package it.tarczynski.konion.price.domain.policy

import it.tarczynski.konion.price.domain.Price
import org.springframework.stereotype.Component

@Component
class ComplexPriceChangePolicy : PriceChangePolicy {

    override fun isValidPriceChange(oldPrice: Price, newPrice: Price): Boolean {
        // wykonuje jakąś skomplikowaną logikę, może siega do bazy, albo wykonuje żądanie do innej uslugi
        return isNewPriceHigherOrEqual(oldPrice, newPrice)
    }

    private fun isNewPriceHigherOrEqual(oldPrice: Price, newPrice: Price) =
        newPrice >= oldPrice
}
