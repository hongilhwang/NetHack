package com.seravie.nyethack

open class Room(val name:String ){
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\r\n" +
                        "위험 수준: $dangerLevel\r\n" +
                        "Creature: ${monster?.description ?: "none."}"
    open fun load() = "아무도 여기에 오지 않았습니다..."
}

open class TownSquare : Room("TownSquare"){
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "땡땡"
    final override fun load() = "당신의 참여를 주민들이 다 함께 환영합니다!"
    public fun ringBell() = "당신의 도착을 종탑에서 알립니다. $bellSound"
}