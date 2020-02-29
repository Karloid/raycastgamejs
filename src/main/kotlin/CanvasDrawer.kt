
import core.Game
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class CanvasDrawer {
    private var lastTickTs: Double
    private var game: Game
    var canvas = document.getElementById("myCanvas") as HTMLCanvasElement;
    var ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    init {
        game = Game()

        lastTickTs = Date().getTime()
    }

    fun drawLoop() {
        val currentTs = Date().getTime()
        val delta = currentTs - lastTickTs
        game.onTick(delta)
        draw(delta)
        window.setTimeout({ drawLoop() }, 64)
    }

    fun draw(delta: Double) {
        game.drawGame(ctx)
    }
}