package it.tarczynski.konion.product.application

import it.tarczynski.konion.price.domain.policy.PriceChangePolicy
import it.tarczynski.konion.product.application.dto.CreateProductRequest
import it.tarczynski.konion.product.application.dto.ListProductResponse
import it.tarczynski.konion.product.application.dto.ProductResponse
import it.tarczynski.konion.product.application.dto.UpdateProductPriceRequest
import it.tarczynski.konion.product.domain.Product
import it.tarczynski.konion.product.domain.toProductId
import it.tarczynski.konion.product.repository.ProductRepository
import it.tarczynski.konion.product.service.ProductNotificationService
import org.springframework.stereotype.Component

@Component
class ProductFacade(
    private val productRepository: ProductRepository,
    private val notificationService: ProductNotificationService,
    private val priceChangePolicy: PriceChangePolicy
) {

    fun listProducts(): ListProductResponse {
        val products: List<Product> = productRepository.findAll()
        return ListProductResponse.from(products)
    }

    fun createProduct(request: CreateProductRequest): ProductResponse {
        return request.toNewProduct()
            .let { productRepository.save(it) }
            .also { notificationService.productCreated(it) }
            .let { ProductResponse.from(it) }
    }

    fun updateProductPrice(
        rawId: String,
        priceRequest: UpdateProductPriceRequest
    ): ProductResponse {
        val product = productRepository.getById(rawId.toProductId())
        return product.updatePrice(priceRequest.newPrice, priceChangePolicy)
            .let { productRepository.save(it) }
            .also { notificationService.productPriceUpdated(it) }
            .let { ProductResponse.from(it) }
    }

    fun withdrawProduct(rawId: String): ProductResponse {
        val product = productRepository.getById(rawId.toProductId())
        return product.withdraw()
            .let { productRepository.save(it) }
            .also { notificationService.productWithdrawn(it) }
            .let { ProductResponse.from(it) }
    }

    fun activateProduct(rawId: String, activationPrice: UpdateProductPriceRequest): ProductResponse {
        val product = productRepository.getById(rawId.toProductId())
        return product.activate(activationPrice.newPrice)
            .let { productRepository.save(it) }
            .also { notificationService.productActivated(it) }
            .let { ProductResponse.from(it) }
    }

}
