package lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductSelectionTest {
    @Test
    fun selectsAvailableProductsInRecommendationOrder() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", false)
        val monitor = Product(3, "Monitor", true)
        val webcam = Product(4, "Webcam", true)
        val products = listOf(keyboard, mouse, monitor, webcam)

        val result = selectProducts(
            products,
            listOf(3L, 2L, 3L, 99L, 1L, 4L),
            listOf(1L, 1L)
        )

        assertEquals(listOf(monitor, webcam), result)
    }

    @Test
    fun returnsEmptyListWhenThereAreNoRecommendations() {
        val products = listOf(Product(1, "Keyboard", true))

        val result = selectProducts(products, emptyList(), emptyList())

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnsEmptyListWhenThereAreNoProducts() {
        val products = emptyList<Product>()
        val result = selectProducts(products, listOf(1L, 2L), listOf(1L))

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnsEmptyListWhenNoProductIsEligible() {
        val keyboard = Product(1, "Keyboard", false)
        val mouse = Product(2, "Mouse", true)
        val monitor = Product(3, "Monitor", true)

        val products = listOf(keyboard, mouse, monitor)

        val result = selectProducts(products, listOf(1L, 2L, 99L, 3L), listOf(2L, 3L))

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun doesNotModifyInputsAndReturnsIndependentResults() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", false)
        val monitor = Product(3, "Monitor", true)

        val products = listOf(keyboard, mouse, monitor)
        val recommendedIds = listOf(3L, 2L, 3L, 1L)
        val excludedIds = listOf(1L)

        val expectedProducts = listOf(keyboard, mouse, monitor)
        val expectedRecommendations = listOf(3L, 2L, 3L, 1L)
        val expectedExclusions = listOf(1L)
        val expectedResult = listOf(monitor)

        val firstResult = selectProducts(products, recommendedIds, excludedIds)
        val secondResult = selectProducts(products, recommendedIds, excludedIds)

        assertEquals(expectedResult, firstResult)
        assertEquals(expectedResult, secondResult)
        assertEquals(expectedProducts, products)
        assertEquals(expectedRecommendations, recommendedIds)
        assertEquals(expectedExclusions, excludedIds)
    }




}
