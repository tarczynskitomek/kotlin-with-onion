package it.tarczynski.konion.product.domain

import it.tarczynski.konion.price.domain.Price
import it.tarczynski.konion.price.domain.policy.PriceChangePolicy
import spock.lang.Specification

import static it.tarczynski.konion.product.domain.ProductStatus.ACTIVE
import static it.tarczynski.konion.product.domain.ProductStatus.NEW
import static it.tarczynski.konion.product.domain.ProductStatus.WITHDRAWN

class ProductSpec extends Specification {

    def "withdraw should fail for new products"() {
        given:
        Product newProduct = ProductBuilder.product()
                .withStatus(NEW)
                .build()

        when:
        newProduct.withdraw()

        then:
        IllegalStateException ex = thrown(IllegalStateException)
        ex.message == 'Cannot withdraw new product'
    }

    def "withdraw should mark the product as withdrawn"() {
        given:
        Product product = ProductBuilder.product()
                .withStatus(status)
                .build()

        when:
        Product withdrawnProduct = product.withdraw()

        then:
        product.status == status
        withdrawnProduct.status == WITHDRAWN

        where:
        status << [ACTIVE, WITHDRAWN]
    }

    def "activate should mark the product as active and set activation price"() {
        given:
        Product product = ProductBuilder.product()
                .withStatus(status)
                .build()

        when:
        Product activeProduct = product.activate(activationPrice)

        then:
        activeProduct.status == ACTIVE
        activeProduct.price == activationPrice

        where:
        status | activationPrice
        NEW    | new Price(BigDecimal.ONE, "PLN")
    }

    def "activation price cannot be zero"() {
        given:
        Product product = ProductBuilder.product().build()

        when:
        product.activate(Price.zero("PLN"))

        then:
        IllegalArgumentException ex = thrown(IllegalArgumentException)
        ex.message == 'Activation price cannot be zero'
    }

    def "updatePrice should change product price if policy allows the change"() {
        given: 'a policy that allows the price change'
        PriceChangePolicy allowPolicy = Mock {
            isValidPriceChange(_, _) >> true
        }

        and: 'a product which price we want to change'
        Product product = ProductBuilder.product().build()

        when: 'a new price is given'
        Product productWithNewPrice = product.updatePrice(expectedPrice, allowPolicy)

        then: 'the expected price is set on the product'
        productWithNewPrice.price == expectedPrice

        where:
        expectedPrice << [new Price(BigDecimal.ONE, "PLN")]
    }

    def "updatePrice should throw given new price that is not allowed by the price change policy"() {
        given: 'a policy that denies price change'
        PriceChangePolicy denyPolicy = Mock {
            isValidPriceChange(_, _) >> false
        }

        and:
        Product product = ProductBuilder.product().build()

        when:
        product.updatePrice(new Price(BigDecimal.ONE, "PLN"), denyPolicy)

        then:
        thrown(IllegalArgumentException)
    }
}
