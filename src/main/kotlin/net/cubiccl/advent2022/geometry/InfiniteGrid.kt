package net.cubiccl.advent2022.geometry

open class InfiniteGrid<T>(
    private val defaultValue: (Position) -> T? = { null }
) {

    private val grid = mutableMapOf<Position, T>()

    fun get(pos: Position): T? {
        return grid[pos] ?: defaultValue.invoke(pos)
    }

    fun set(pos: Position, item: T): T? {
        val previous = get(pos)
        grid[pos.clone()] = item
        return previous ?: defaultValue.invoke(pos)
    }

    fun set(x: Int, y: Int, item: T): T? {
        return set(Position(x, y), item)
    }

    fun remove(pos: Position): T? {
        val previous = get(pos)
        grid.remove(pos)
        return previous ?: defaultValue.invoke(pos)
    }

    private fun getAllPos(): Set<Position> {
        return grid.keys
    }

    fun findMinX(): Int {
        return getAllPos().minOf { it.x }
    }

    fun findMaxX(): Int {
        return getAllPos().maxOf { it.x }
    }

    fun findMinY(): Int {
        return getAllPos().minOf { it.y }
    }

    fun findMaxY(): Int {
        return getAllPos().maxOf { it.y }
    }

}