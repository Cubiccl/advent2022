package net.cubiccl.advent2022.day9

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.geometry.Position

class Day9 : AbstractDay(9) {
    override fun play(): String {
        val rope = Rope()
        val instructions = readPuzzleInput().getLinesAs(RopeInstruction::parse)
        val visited = mutableSetOf<Position>()
        instructions.forEach { it.execute(rope, visited) }
        return visited.size.toString()
    }

    companion object {
        val knotCount = if (isFirstPart) 1 else 9
    }

}
