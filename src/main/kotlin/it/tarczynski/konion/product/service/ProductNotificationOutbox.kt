package it.tarczynski.konion.product.service

import it.tarczynski.konion.config.loggerFor
import it.tarczynski.konion.product.domain.Product
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class ProductNotificationOutbox : ProductNotificationService {

    private val logger: Logger = loggerFor<ProductNotificationService>()

    override fun productCreated(product: Product) {
        logger.info("Scheduled product created notification [{}]", product)
    }

    override fun productPriceUpdated(product: Product) {
        logger.info("Scheduled product price updated notification [{}]", product)
    }

    override fun productWithdrawn(product: Product) {
        logger.info("Scheduled product withdrawn notification [{}]", product)
    }

    override fun productActivated(product: Product) {
        logger.info("Scheduled product activated notification [{}]", product)
    }
}
