package core

import org.w3c.dom.CanvasRenderingContext2D
import utils.Log
import utils.Point2D

class Game {

    private val drawer = GameDrawer(this)
    val level = Level()
    val input = InputController()
    private var tick = 0
    val player: Player

    init {
        level.generateMap()

        player = Player(level.playerSpawns.first().copy() + Point2D(0.5, 0.5))
    }

    fun onTick(delta: Double) {
        tick++
        val rotateAngle = 0.03
        if (input.isLeftTurn()) {
            player.angle -= rotateAngle
        }

        if (input.isRightTurn()) {
            player.angle += rotateAngle
        }

        if (input.isForward() xor input.isBackward()) {
            val deltaMovement = Point2D(angle = player.angle).length(0.04)
            if (input.isBackward()) {
                Log.myLog("backwards before = deltaMovement=$deltaMovement")
                deltaMovement.mul(-1.0)
                Log.myLog("backwards after = deltaMovement=$deltaMovement")
            }
            player.pos.plus(deltaMovement)
        }
    }

    fun drawGame(ctx: CanvasRenderingContext2D) {
        drawer.drawGame(ctx)
    }
}
