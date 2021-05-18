package it.tarczynski.konion.price.domain.policy

import it.tarczynski.konion.price.domain.Price
import org.springframework.stereotype.Component

@Component
class ComplexPriceChangePolicy : PriceChangePolicy {

    override fun verify(oldPrice: Price, newPrice: Price) {
        // wykonuje jakąś skomplikowaną logikę, może siega do bazy, albo wykonuje żądanie do innej uslugi
        if (isNewPriceLower(oldPrice, newPrice)) {
            throw IllegalArgumentException("We only make our prices higher!")
        }
    }

    private fun isNewPriceLower(oldPrice: Price, newPrice: Price) =
        oldPrice > newPrice
}
