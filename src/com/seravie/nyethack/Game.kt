package com.seravie.nyethack

fun main(args: Array<String>){

    Game.play()

}

private fun castFireball(numFireballs: Int = 2) =  println("한 덩어리의 파이어볼이 나타난다. (x$numFireballs)")



object Game{

    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    init{
        println("방문을 환영합니다.")
        player.castFireball()
    }

    fun play(){
        while(true){
            println(currentRoom.description())
            println(currentRoom.load())

            // 플레이어의 상태 출력
            printPlayerStatus(player)

            print("> 명령을 입력하세요: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player){
        println("(Aura: ${player.auraColor()}" +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg:String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, {""})

        fun processCommand() = when( command.toLowerCase()){
            else -> commandNotFound()
        }

        private fun commandNotFound() = "적합하지 않은 명령입니다!"
    }
}


fun performCombat (){
    println("적군이 없다!");
}

fun performCombat (enemyName: String){
    println("적군이 없다!");
}

fun performCombat (enemyName: String, isBlessed: Boolean){
    if( isBlessed ){
        println("$enemyName 과 전투를 시작함. 축복을 받음!");
    }else{
        println("$enemyName 과 전투를 시작함.");
    }
}