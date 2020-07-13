class Weapon(val name: String)
class Player{
    var weapon: Weapon? = Weapon("Ebony Kris")

    fun printWeaponName() {
            println(weapon?.also{it.name})
    }
}