package core

import org.w3c.dom.CanvasRenderingContext2D

private const val MINI_MAP_TILE_SIZE = 8
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


        ctx.fillStyle = "#55ff1011";
        ctx.fillRect(0.0, .0, MINI_MAP_TILE_SIZE * MAP_SIZE.toDouble(), MINI_MAP_TILE_SIZE * MAP_SIZE.toDouble())
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
