package core

enum class Tile private constructor(var discriminant: Int) {
    EMPTY(0),
    WALL(1),
    PLAYER_SPAWN(5);

    fun isOpaque(): Boolean {
        if (this == EMPTY || this == PLAYER_SPAWN) {
            return false
        }
        return true
    }
}
