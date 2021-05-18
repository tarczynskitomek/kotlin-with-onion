package it.tarczynski.konion.product.infrastructure.web

import it.tarczynski.konion.product.application.ProductFacade
import it.tarczynski.konion.product.application.dto.CreateProductRequest
import it.tarczynski.konion.product.application.dto.ListProductResponse
import it.tarczynski.konion.product.application.dto.ProductResponse
import it.tarczynski.konion.product.application.dto.UpdateProductPriceRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/products"])
class ProductEndpoint(
    private val productFacade: ProductFacade
) {

    @GetMapping
    fun listProducts(): ListProductResponse {
        return productFacade.listProducts()
    }

    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ProductResponse {
        return productFacade.createProduct(request)
    }

    @PostMapping("/{productId}/activate")
    fun activateProduct(@PathVariable productId: String): ProductResponse {
        return productFacade.activateProduct(productId)
    }

    @PostMapping("/{productId}/withdraw")
    fun withdrawProduct(@PathVariable productId: String): ProductResponse {
        return productFacade.withdrawProduct(productId)
    }

    @PutMapping("/{productId}/price")
    fun updateProductPrice(
        @PathVariable productId: String,
        @RequestBody request: UpdateProductPriceRequest
    ): ProductResponse {
        return productFacade.updateProductPrice(productId, request)
    }
}
