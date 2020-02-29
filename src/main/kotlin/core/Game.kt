package core

import org.w3c.dom.CanvasRenderingContext2D

class Game {

    private val drawer = GameDrawer(this)
    val level = Level()

    init {
        level.generateMap()
    }

    fun onTick(delta: Double) {

    }

    fun drawGame(ctx: CanvasRenderingContext2D) {
        drawer.drawGame(ctx)
    }

}
