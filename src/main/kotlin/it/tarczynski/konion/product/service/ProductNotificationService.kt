package it.tarczynski.konion.product.service

import it.tarczynski.konion.product.domain.Product

interface ProductNotificationService {

    fun productCreated(product: Product)

    fun productPriceUpdated(product: Product)

    fun productWithdrawn(product: Product)

    fun productActivated(product: Product)
}
