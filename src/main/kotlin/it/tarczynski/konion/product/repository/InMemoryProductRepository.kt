package it.tarczynski.konion.product.repository

import it.tarczynski.konion.product.domain.Product
import it.tarczynski.konion.product.domain.ProductId
import org.springframework.stereotype.Repository
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryProductRepository : ProductRepository {

    private val products = ConcurrentHashMap<ProductId, Product>()

    override fun findAll(): List<Product> {
        return products.values.toList()
    }

    override fun getById(productId: ProductId): Product {
        return products[productId] ?: throw IllegalStateException("Product not found [$productId]")
    }

    override fun save(product: Product): Product {
        products[product.id] = product
        return product
    }
}
