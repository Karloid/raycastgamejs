package core

import org.w3c.dom.CanvasRenderingContext2D
import utils.Point2D

private const val MINI_MAP_TILE_SIZE = 16
val PLAYER_SIZE = MINI_MAP_TILE_SIZE / 2

class GameDrawer(val game: Game) {
    lateinit var ctx: CanvasRenderingContext2D
    fun drawGame(curCtx: CanvasRenderingContext2D) {
        ctx = curCtx
        drawLevelMap()
    }

    private fun drawLevelMap() {
        ctx.clearRect(0.0, .0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())

        drawMiniMap()
    }

    private fun drawMiniMap() {
        game.level.map.fori { x, y, v ->
            when (v) {
                Tile.WALL -> {
                    drawTileWall(x, y)
                }
                else -> {
                }
            }
        }


        drawPlayer(game.player)
        drawRayCast(game.player)


        ctx.fillStyle = "#55ff1011";
        ctx.fillRect(0.0, .0, MINI_MAP_TILE_SIZE * MAP_SIZE.toDouble(), MINI_MAP_TILE_SIZE * MAP_SIZE.toDouble())
    }

    private fun drawRayCast(player: Player) {
        val (x, y) = player.pos
        val angle = player.angle

        val rayCountOneSide = 5
        val fov = 1.0
        val stepAngle = fov / rayCountOneSide

        val totalRaysCount = rayCountOneSide * 2 + 1

        ctx.strokeStyle = "#00ff00";
        ctx.beginPath();       // Start a new path
        repeat(totalRaysCount) { i ->
            val rayIndex = i - rayCountOneSide
            val ray = Point2D(angle + rayIndex * stepAngle).length(MAP_SIZE * ctx.canvas.width * 1.5)

            val rayStart = player.pos.copy()

            val result = doDrawRayCast(rayStart, ray.copy().length(0.01), ray.length())

            val rayEnd = result.endPos
            
            ctx.moveTo(rayStart.x * MINI_MAP_TILE_SIZE, rayStart.y * MINI_MAP_TILE_SIZE);
            ctx.lineTo(rayEnd.x * MINI_MAP_TILE_SIZE, rayEnd.y * MINI_MAP_TILE_SIZE)
        }

        ctx.stroke();
    }

    private fun doDrawRayCast(rayStart: Point2D, step: Point2D, maxLength: Double): RayCastResult {
        val stepLength = step.length()

        var pointToCheck = rayStart.copy()
        var curDistance = 0.0
        while (true) {
            val currentTitle = game.level.map.getNoRound(pointToCheck)

            @Suppress("FoldInitializerAndIfToElvis")
            if (currentTitle == null) {
                return RayCastResult(pointToCheck, Tile.WALL, curDistance)
            }

            if (currentTitle.isOpaque()) {
                return RayCastResult(pointToCheck, currentTitle, curDistance)
            }

            pointToCheck = pointToCheck.plus(step)
            curDistance += stepLength

            if (curDistance > maxLength) {
                return RayCastResult(pointToCheck, Tile.WALL, curDistance)
            }
        }


    }

    private fun drawPlayer(player: Player) {
        ctx.fillStyle = "#FF0000"

        val x = player.pos.x
        val y = player.pos.y
        ctx.fillRect(
            x * MINI_MAP_TILE_SIZE - PLAYER_SIZE / 2,
            y * MINI_MAP_TILE_SIZE - PLAYER_SIZE / 2,
            PLAYER_SIZE.toDouble(),
            PLAYER_SIZE.toDouble()
        )
    }

    private fun drawTileWall(x: Int, y: Int) {
        ctx.fillStyle = "#000";
        ctx.fillRect(x * MINI_MAP_TILE_SIZE, y * MINI_MAP_TILE_SIZE, MINI_MAP_TILE_SIZE, MINI_MAP_TILE_SIZE);
    }
}

private fun CanvasRenderingContext2D.fillRect(x: Int, y: Int, w: Int, h: Int) {
    fillRect(x.toDouble(), y.toDouble(), w.toDouble(), h.toDouble())
}
