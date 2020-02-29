package core

import utils.PlainArray
import kotlin.random.Random

val MAP_SIZE = 32

class Level {

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
        map.fori { x, y, v ->
            if (v == Tile.EMPTY && Random.nextFloat() < 0.2) {
                map[x, y] = Tile.PLAYER_SPAWN
                return@fori
            }
        }

        map.fori { x, y, v ->
            if (v == Tile.EMPTY) {
                map[x, y] = Tile.PLAYER_SPAWN
                return@fori
            }
        }
    }
}
