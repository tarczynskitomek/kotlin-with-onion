package it.tarczynski.konion.price.domain

import java.lang.IllegalStateException
import java.math.BigDecimal

fun BigDecimal.toPrice(currency: String): Price {
    return Price(this, currency)
}

data class Price(
    val value: BigDecimal,
    val currency: String = "PLN"
) : Comparable<Price> {

    override fun compareTo(other: Price): Int {
        if (other.currency != this.currency) {
            throw IllegalStateException("Incomparable currencies")
        }
        return this.value.compareTo(other.value)
    }

    fun isZero(): Boolean {
        return BigDecimal.ZERO == value
    }

    companion object {

        @JvmStatic
        fun zero(currency: String): Price {
            return Price(BigDecimal.ZERO, currency)
        }

    }
}
