package net.cubiccl.advent2022

abstract class AbstractDay(val id: Int) {
    abstract fun play(): String

    fun readPuzzleInput(id: String = "data"): PuzzleInput {
        return PuzzleInput(id, this)
    }
}