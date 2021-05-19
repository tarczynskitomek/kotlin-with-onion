package it.tarczynski.konion.product.application.dto

import it.tarczynski.konion.product.domain.Product

data class ListProductResponse(
    val products: List<ProductResponse>
) {

    companion object {

        fun from(products: List<Product>): ListProductResponse {
            return ListProductResponse(products.map { ProductResponse.from(it) })
        }
    }
}
