package it.tarczynski.konion.product.application.dto

data class ListProductResponse(
    val products: List<ProductResponse>
) {

    companion object {

        fun from(products: List<ProductResponse>): ListProductResponse {
            return ListProductResponse(products)
        }
    }
}
