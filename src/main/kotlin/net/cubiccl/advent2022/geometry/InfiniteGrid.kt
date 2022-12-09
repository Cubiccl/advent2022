package net.cubiccl.advent2022.geometry

open class InfiniteGrid<T> {

    private val grid = mutableMapOf<Position<Int>, T>()

    fun get(pos: Position<Int>): T? {
        return grid[pos]
    }

    fun set(pos: Position<Int>, item: T): T? {
        val previous = get(pos)
        grid[pos.clone()] = item
        return previous
    }

    fun set(x: Int, y: Int, item: T): T? {
        return set(Position(x, y), item)
    }

    fun remove(pos: Position<Int>): T? {
        val previous = get(pos)
        grid.remove(pos)
        return previous
    }

}