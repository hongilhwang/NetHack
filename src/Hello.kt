import java.io.File

fun main (args: Array<String>) {
    println("안녕하세요.")
    File("data/aaa.txt").printWriter().use{out -> out.println("test")}
}
