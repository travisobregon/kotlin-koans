package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product) = customers.filter { it.orderedProducts.contains(product) }.toSet()

fun Customer.getMostExpensiveDeliveredProduct() = orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }

fun Shop.getNumberOfTimesProductWasOrdered(product: Product) = customers.flatMap { it.orders.flatMap { it.products } }.toList().count { it == product }
