package core

import org.w3c.dom.CanvasRenderingContext2D
import utils.Log
import utils.PlainArray
import utils.Point2D
import utils.then

val PLAYER_GAME_SIZE = 0.1

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

        if (input.isLeftTurn() xor input.isRightTurn()) {
            var rotateAngle = 0.03
            if (input.isSprint()) {
                rotateAngle *= 2
            }

            if (input.isLeftTurn()) {
                player.angle -= rotateAngle
            } else {
                player.angle += rotateAngle
            }
        }

        if (input.isForward() xor input.isBackward()) {
            var dist = 0.04
            if (input.isSprint()) {
                dist *= 2
            }
            val deltaMovement = Point2D(angle = player.angle).length(dist)
            if (input.isBackward()) {
                deltaMovement.mul(-1.0)
            }
            movePlayerAndCheckCollisions(deltaMovement)
        }
    }

    private fun movePlayerAndCheckCollisions(deltaMovement: Point2D) {
        var oldPos = player.pos.copy()

        player.pos.plus(deltaMovement.x, 0.0)
        if (checkCollisions(player.pos, PLAYER_GAME_SIZE)) {
            player.pos.set(oldPos)
        }
        oldPos = player.pos.copy()
        player.pos.plus(0.0, deltaMovement.y)
        if (checkCollisions(player.pos, PLAYER_GAME_SIZE)) {
            player.pos.set(oldPos)
        }
    }

    //TODO move to helpers
    private fun checkCollisions(pos: Point2D, size: Double): Boolean {
        return isCollideWalls(pos, size.toDouble(), level.map)
    }

    fun drawGame(ctx: CanvasRenderingContext2D) {
        drawer.drawGame(ctx)
    }

    //TODO move to helpers
    fun isCollideWalls(
        pos: Point2D,
        size: Double,
        tiles: PlainArray<Tile>
    ): Boolean {
        val halfSize = size / 2
        var x = (pos.x - halfSize).toInt()
        var y = (pos.y - halfSize).toInt()
        var tile = tiles[x, y]
        isWallOrNull(tile).then {
            return true
        }

        x = (pos.x - halfSize).toInt()
        y = (pos.y + halfSize).toInt()
        tile = tiles[x, y]
        isWallOrNull(tile).then {
            return true
        }

        x = (pos.x + halfSize).toInt()
        y = (pos.y + halfSize).toInt()
        tile = tiles[x, y]
        isWallOrNull(tile).then {
            return true
        }

        x = (pos.x + halfSize).toInt()
        y = (pos.y - halfSize).toInt()
        tile = tiles[x, y]
        isWallOrNull(tile).then {
            return true
        }

        return false
    }

    private fun isWallOrNull(tile: Tile?) = tile === Tile.WALL || tile === null

}
