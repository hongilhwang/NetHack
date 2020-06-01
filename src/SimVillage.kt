
fun main(args : Array<String>){

    runSimulation("김선달", ::printConstructionCost) { playerName, numBuildings ->
        val currentYear: Int = 2019
        println("$numBuildings 채의 건물이 추가됨");
        "SimVillage 방문을 환영합니다, $playerName 님! (copyright $currentYear)"
    }

}

inline fun runSimulation(playerName: String, costPrinter: (Int) -> Unit, greetingFunction: (String, Int)-> String ){
    val numBuildings = (1..3).shuffled().last() // 1, 2, 3 중 하나를 무작위로 선택한다.
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}

fun printConstructionCost( numBuildings: Int ){
    val cost = 500;
    println("건축 비용: ${cost * numBuildings}");
}