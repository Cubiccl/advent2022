package net.cubiccl.advent2022.geometry

data class Position<T>(var x: T, var y: T) {
    fun clone(): Position<T> {
        return Position(x, y)
    }

    override fun toString(): String {
        return "[$x, $y]"
    }
}