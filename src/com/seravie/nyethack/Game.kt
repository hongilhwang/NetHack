package com.seravie.nyethack

import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main(args: Array<String>){

    Game.play()

}

private fun castFireball(numFireballs: Int = 2) =  println("한 덩어리의 파이어볼이 나타난다. (x$numFireballs)")



object Game{

    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init{
        println("방문을 환영합니다.")
        player.castFireball()
    }

    private fun move(directionInput: String) =
        try{
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if(!newPosition.isInBounds){
                throw IllegalStateException("$direction 쪽 방향이 범위를 벗어남.")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, $direction 방향의 ${newRoom.name}로 이동했습니다."
        }catch (e: Exception){
            "잘못된 방향임: $directionInput."
        }

    private fun fight() = currentRoom.monster?.let{
        while(player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "전투가 끝났음."
    } ?: "여기에는 싸울 괴물이 없습니다..."

    private fun slay(monster: Monster){
        println("${monster.name} -- ${monster.attack(player)} 손상을 입힘!")
        println("${player.name} -- ${player.attack(monster)} 손상을 입힘!")

        if( player.healthPoints <= 0){
            println(">>>> 당신은 졌습니다! 게임을 종료합니다.. <<<<<")
            exitProcess(0)
        }

        if(monster.healthPoints <= 0){
            println(">>>> ${monster.name} --- 격퇴됨! <<<<<<")
            currentRoom.monster = null
        }
    }

    fun play(){
        var isContinue = true
        while(isContinue){
            println(currentRoom.description())
            println(currentRoom.load())

            // 플레이어의 상태 출력
            printPlayerStatus(player)

            print("> 명령을 입력하세요: ")
            try{
                println(GameInput(readLine()).processCommand())
            }catch(e : Exception){
                println(e)
                isContinue = false
            }

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
            "fight" -> fight()
            "move" -> move(argument)
            "map" -> showMap()
            "ring" ->  strikeBell()
            "quit","exit" -> exitProcess(0)
            else -> commandNotFound()
        }

        private fun commandNotFound() = "적합하지 않은 명령입니다!"

        private fun showMap(){
            worldMap.forEach{ println(it.map{ location -> if(location === currentRoom)  "X" else "O"}) }
        }

        private fun strikeBell() = if(currentRoom is TownSquare) (currentRoom as TownSquare).ringBell() else "주변에 종이 보이지 않습니다."
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