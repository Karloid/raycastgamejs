package core

import utils.*
import kotlin.random.Random

val MAP_SIZE = 32

class Level {

    val playerSpawns = mutableListOf<Point2D>()

    val map = PlainArray(MAP_SIZE, MAP_SIZE) { Tile.EMPTY }

    fun generateMap() {

        generateSimpleMaze()

        //generateRandomMap()

        generatePlayerSpawn()
    }

    private fun generateSimpleMaze() {
        map.fori { x, y, v ->
            if (Random.nextFloat() < 0.3) {
                map[x, y] = Tile.WALL
            }
        }

        map.fori { x, y, v ->
            val pos = Point2D(x, y)
            if (v == Tile.WALL &&
                map.get(pos.copy().applyDir(Direction.UP)) == Tile.EMPTY &&
                map.get(pos.copy().applyDir(Direction.DOWN)) == Tile.EMPTY &&
                map.get(pos.copy().applyDir(Direction.RIGHT)) == Tile.EMPTY &&
                map.get(pos.copy().applyDir(Direction.LEFT)) == Tile.EMPTY
            ) {
                map.set(pos, Tile.EMPTY)
            }
        }
    }

    private fun generateRandomMap() {
        map.fori { x, y, v ->
            if (Random.nextFloat() < 0.2) {
                map[x, y] = Tile.WALL
            }
        }
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
