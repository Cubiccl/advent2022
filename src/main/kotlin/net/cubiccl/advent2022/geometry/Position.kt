package net.cubiccl.advent2022.geometry

import kotlin.math.abs

data class Position(var x: Int, var y: Int) {
    fun clone(): Position {
        return Position(x, y)
    }

    override fun toString(): String {
        return "[$x, $y]"
    }
    fun manhattan(to: Position): Int {
        return abs(x - to.x) + abs(y - to.y)
    }

    fun minus(range: Int): Position {
        return Position(x - range, y - range)
    }

    fun plus(range: Int): Position {
        return Position(x + range, y + range)
    }
}