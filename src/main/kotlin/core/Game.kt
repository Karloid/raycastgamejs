package core

import org.w3c.dom.CanvasRenderingContext2D
import utils.Point2D

class Game {

    private val drawer = GameDrawer(this)
    val level = Level()

    val player: Player

    init {
        level.generateMap()

        player = Player(level.playerSpawns.first().copy() + Point2D(0.5, 0.5))
    }

    fun onTick(delta: Double) {
        player.angle += 0.05
    }

    fun drawGame(ctx: CanvasRenderingContext2D) {
        drawer.drawGame(ctx)
    }
}
