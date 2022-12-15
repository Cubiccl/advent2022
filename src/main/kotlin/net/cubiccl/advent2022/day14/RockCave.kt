package net.cubiccl.advent2022.day14

import net.cubiccl.advent2022.geometry.InfiniteGrid
import net.cubiccl.advent2022.geometry.Position
import kotlin.math.max
import kotlin.math.min

class RockCave : InfiniteGrid<RockCave.RockCaveTile>({ RockCaveTile.AIR }) {

    private fun setRock(position: Position) {
        set(position, RockCaveTile.ROCK)
    }

    fun readPath(path: List<Position>) {
        (0 until path.lastIndex).forEach {
            val source = path[it]
            val destination = path[it + 1]
            getWholePath(source, destination).forEach(this::setRock)
        }
    }

    private fun getWholePath(source: Position, destination: Position): List<Position> {
        if (source.x == destination.x) {
            return (min(source.y, destination.y)..max(source.y, destination.y)).map { Position(source.x, it) }
        }
        return (min(source.x, destination.x)..max(source.x, destination.x)).map { Position(it, source.y) }
    }

    fun addFloor() {
        val floorY = findMaxY() + 2
        val minX = findMinX()
        val maxX = findMaxX()
        //Just to be sure, make a really big floor
        val xRange = (maxX - minX) * 5
        val start = Position(minX - xRange, floorY)
        val end = Position(maxX + xRange, floorY)
        //println("Range: $xRange, start: $start, end: $end")
        readPath(listOf(start, end))
    }

    enum class RockCaveTile {
        AIR, ROCK, SAND
    }

}
