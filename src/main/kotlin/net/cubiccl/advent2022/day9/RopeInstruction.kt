package net.cubiccl.advent2022.day9

import net.cubiccl.advent2022.day9.Day9.Companion.knotCount
import net.cubiccl.advent2022.geometry.Direction
import net.cubiccl.advent2022.geometry.Position

class RopeInstruction(private val direction: Char, private val distance: Int) {

    fun execute(rope: Rope, visited: MutableSet<Position>) {
        for (i in 1..distance) {
            moveHead(rope)
            println("Head   : ${rope.getHeadPosition()}")
            for (knot in 1..knotCount) {
                relocateKnot(rope, knot)
                println("Knot #$knot: ${rope.getKnotPosition(knot)}")
            }
            visited.add(rope.getKnotPosition(knotCount))
        }
    }

    private fun moveHead(rope: Rope) {
        val position = rope.getHeadPosition()
        when (direction) {
            'D' -> position.y--
            'U' -> position.y++
            'L' -> position.x--
            'R' -> position.x++
        }
        rope.moveHeadTo(position)
    }

    private fun relocateKnot(rope: Rope, knot: Int) {
        var directions = rope.getDirectionsToParent(knot)
        while (directions.isNotEmpty()) {
            val currentPos = rope.getKnotPosition(knot)
            if (directions.contains(Direction.DOWN)) {
                currentPos.y--
            }
            if (directions.contains(Direction.UP)) {
                currentPos.y++
            }
            if (directions.contains(Direction.LEFT)) {
                currentPos.x--
            }
            if (directions.contains(Direction.RIGHT)) {
                currentPos.x++
            }
            rope.moveKnotTo(knot, currentPos)
            directions = rope.getDirectionsToParent(knot)
        }
    }

    companion object {
        fun parse(line: String): RopeInstruction {
            return RopeInstruction(line[0], line.substring(2).toInt())
        }
    }

}
