package net.cubiccl.advent2022.geometry

class Grid<T>(val width: Int, val height: Int, initializer: (Position) -> T) {

    private val grid: List<MutableList<T>>
    val maxX: Int
        get() {
            return width - 1
        }
    val maxY: Int
        get() {
            return height - 1
        }

    init {
        grid = buildList {
            for (x in 0 until width) {
                add(mutableListOf())
                for (y in 0 until height) {
                    last().add(initializer(Position(x, y)))
                }
            }
        }
    }

    fun get(pos: Position): T {
        return get(pos.x, pos.y)
    }

    fun get(x: Int, y: Int): T {
        return grid[x][y]
    }

    fun set(x: Int, y: Int, item: T): T {
        return grid[x].set(y, item)
    }

    fun find(value: T): Position? {
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (get(x, y) == value) {
                    return Position(x, y)
                }
            }
        }
        return null
    }

    fun findAll(value: T): List<Position> {
        val found = mutableListOf<Position>()
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (get(x, y) == value) {
                    found.add(Position(x, y))
                }
            }
        }
        return found
    }

    fun isInBounds(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < width && y < height
    }

}