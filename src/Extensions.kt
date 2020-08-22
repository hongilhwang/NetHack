fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easyPrint(): T {
    println(this)
    return this
}

fun main(args: Array<String>) {
    "마드리갈이 그 건물에서 나왔습니다".easyPrint().addEnthusiasm(3)
}