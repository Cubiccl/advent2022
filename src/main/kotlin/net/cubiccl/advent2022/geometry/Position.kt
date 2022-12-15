package net.cubiccl.advent2022.geometry

import kotlin.math.abs

data class Position(var x: Int, var y: Int) {
    fun clone(): Position {
        return Position(x, y)
    }

    override fun toString(): String {
        return "[$x, $y]"
    }
}