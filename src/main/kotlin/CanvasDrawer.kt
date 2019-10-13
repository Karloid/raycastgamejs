import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class CanvasDrawer {
    var canvas = document.getElementById("myCanvas") as HTMLCanvasElement;
    var ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    init {

    }

    fun drawStuff() {
        draw()
    }

    fun draw() {
        val d = Date();
        val n = d.getTime()
        val ratio = (n % 1000) / 1000.0
        ctx.fillStyle = "#FFFF00";
        ctx.fillRect(0.0, 0.0, 150.0, 75.0);
        ctx.fillStyle = "#FFF0FF";
        ctx.fillRect(0.0, 0.0, 130 * ratio, 55.0);

        window.setTimeout({ draw() }, 64)
    }
}