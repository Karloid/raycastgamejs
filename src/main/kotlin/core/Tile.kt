package core

enum class Tile private constructor(var discriminant: Int) {
    EMPTY(0),
    WALL(1),
    PLATFORM(2),
    LADDER(3),
    JUMP_PAD(4),
    PLAYER_SPAWN(5)
}
