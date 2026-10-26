package lab2

fun selectProducts(
    products: List<Product>,
    recommendedIds: List<Long>,
    excludedIds: List<Long>
): List<Product> {
    val productsById = products.associateBy { it.id } // поиск по id дает О(1) в среднем
    val excluded = excludedIds.toHashSet() // убираем повторы
    val addedIds = HashSet<Long>() // защита от повторов
    val result = ArrayList<Product>()

    // порядок обхода повторяет порядок рекомендаций
    for (id in recommendedIds) {
        val product = productsById[id] ?: continue

        if (!product.available) continue
        if (id in excluded) continue
        if (!addedIds.add(id)) continue

        result.add(product)
    }

    return result
}
