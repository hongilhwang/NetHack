package com.seravie.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = listOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\r\n")
val patronGold = mutableMapOf<String, Double>()


private fun String.toDragonSpeak(): String = this.replace(Regex("[aeiou]")) {
    when (it.value) {
        "a" -> "4"
        "e" -> "3"
        "i" -> "1"
        "o" -> "0"
        "u" -> "|_|"
        else -> it.value
    }
}


fun main(args: Array<String>) {

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name

    }

    uniquePatrons.forEach { patronGold[it] = 6.0 }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }

    displayPatronBalances()

}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")

    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}


private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)

    println("$patronName 은 $tavernMaster 에게 주문한다.")

    val (type, name, price) = menuData.split(',')

    val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName 이 감탄한다: ${"와, $name 진짜 좋구나!".toDragonSpeak()}"
    } else {
        "$patronName 이 말한다: 감사합니다 $name."
    }

    println(phrase)

}
