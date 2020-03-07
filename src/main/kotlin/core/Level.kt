package core

import utils.Log
import utils.PlainArray
import utils.Point2D
import utils.myRandomOrNull
import kotlin.random.Random

val MAP_SIZE = 32

class Level {

    val playerSpawns = mutableListOf<Point2D>()

    val map = PlainArray(MAP_SIZE, MAP_SIZE) { Tile.EMPTY }

    fun generateMap() {
        map.fori { x, y, v ->
            if (Random.nextFloat() < 0.2) {
                map[x, y] = Tile.WALL
            }
        }

        generatePlayerSpawn()
    }

    private fun generatePlayerSpawn() {
        val pos = findRandomEmptyPlace()

        map[pos.roundX, pos.roundY] = Tile.PLAYER_SPAWN
        playerSpawns.add(pos)
    }

    @Suppress("unused")
    fun findRandomEmptyPlace(): Point2D {
        val emptyPlaces = mutableListOf<Point2D>()
        map.fori { x, y, v ->
            //Log.myLog("findRandomEmptyPlace $x $y $v")
            if (v == Tile.EMPTY) {
                emptyPlaces.add(Point2D(x, y))
            }
        }

        return emptyPlaces.myRandomOrNull() ?: Point2D(2, 2)
    }
}
