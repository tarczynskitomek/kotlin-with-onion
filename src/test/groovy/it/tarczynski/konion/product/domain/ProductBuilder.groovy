package it.tarczynski.konion.product.domain


import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import it.tarczynski.konion.price.domain.Price

@Builder(prefix = "with", builderStrategy = SimpleStrategy)
class ProductBuilder {

    UUID id = UUID.randomUUID()
    String name = "Lorem Ipsum Product"
    String priceCurrency = "PLN"
    BigDecimal priceValue = BigDecimal.ONE
    ProductStatus status = ProductStatus.ACTIVE

    static ProductBuilder product() {
        new ProductBuilder()
    }

    Product build() {
        new Product(
                id, new Price(priceValue, priceCurrency), name, status
        )
    }
}
