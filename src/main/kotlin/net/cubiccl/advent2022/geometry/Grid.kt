package net.cubiccl.advent2022.geometry

class Grid<T>(val width: Int, val height: Int, initializer: (Position<Int>) -> T) {

    private val grid: List<MutableList<T>>
    val maxX: Int
        get() { return width - 1 }
    val maxY: Int
        get() { return height - 1 }

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

    fun get(pos: Position<Int>): T {
        return get(pos.x, pos.y)
    }

    fun get(x: Int, y: Int): T {
        return grid[x][y]
    }

    fun set(x: Int, y: Int, item: T): T {
        return grid[x].set(y, item)
    }

}