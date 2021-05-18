package it.tarczynski.konion.product.application.dto

import it.tarczynski.konion.price.domain.Price
import it.tarczynski.konion.product.domain.Product
import it.tarczynski.konion.product.domain.ProductId
import it.tarczynski.konion.product.domain.ProductStatus

class CreateProductRequest(
    private val name: String,
    private val saleCurrency: String,
) {

    fun toNewProduct(): Product {
        return Product(
            id = ProductId.next(),
            price = Price.zero(saleCurrency),
            name = name,
            status = ProductStatus.NEW,
        )
    }

}
