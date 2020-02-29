package core

import org.w3c.dom.CanvasRenderingContext2D

class GameDrawer(val game: Game) {
    fun drawGame(ctx: CanvasRenderingContext2D) {

        drawLevelMap(ctx)
    }

    private fun drawLevelMap(ctx: CanvasRenderingContext2D) {
        ctx.fillStyle = "#FFFF22";
        ctx.fillRect(0.0, 0.0, 150.0, 75.0);
        ctx.fillStyle = "#FF33FF";
        ctx.fillRect(0.0, 0.0, 130 * 1.0, 55.0);
    }
}
