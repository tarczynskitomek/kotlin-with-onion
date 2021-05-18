package it.tarczynski.konion.product.repository

import it.tarczynski.konion.product.domain.Product
import it.tarczynski.konion.product.domain.ProductId

interface ProductRepository {

    fun findAll(): List<Product>

    fun getById(productId: ProductId): Product

    fun save(product: Product): Product

}
