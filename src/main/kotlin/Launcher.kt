import kotlin.js.Date

fun main() {
    println("Hello world-" + Date())

    var str = ""
    for (i in 1..100) {
        println(str)
        str += "."
    }

    CanvasDrawer().drawStuff()
}

