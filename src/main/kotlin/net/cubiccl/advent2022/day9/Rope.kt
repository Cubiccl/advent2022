package net.cubiccl.advent2022.day9

import net.cubiccl.advent2022.day9.Day9.Companion.knotCount
import net.cubiccl.advent2022.geometry.Direction
import net.cubiccl.advent2022.geometry.Position
import kotlin.math.abs

class Rope {

    private val components = mutableListOf<Position>()

    init {
        components.add(Position(0, 0))
        for (knot in 1..knotCount) {
            components.add(Position(0, 0))
        }
    }

    fun getHeadPosition(): Position {
        return components[0].clone()
    }

    fun getKnotPosition(knot: Int): Position {
        return components[knot].clone()
    }

    fun moveHeadTo(position: Position) {
        components[0] = position.clone()
    }

    fun moveKnotTo(knot: Int, position: Position) {
        components[knot] = position.clone()
    }

    private fun isKnotTooFar(knot: Int): Boolean {
        val parentPos = getKnotPosition(knot - 1)
        val knotPos = getKnotPosition(knot)
        return abs(parentPos.x - knotPos.x) > 1 || abs(parentPos.y - knotPos.y) > 1
    }

    fun getDirectionsToParent(knot: Int): List<Direction> {
        if (!isKnotTooFar(knot)) {
            return emptyList()
        }
        val parentPos = getKnotPosition(knot - 1)
        val knotPos = getKnotPosition(knot)
        return buildList {
            if (parentPos.x - knotPos.x > 0 ) {
                add(Direction.RIGHT)
            }
            if (parentPos.x - knotPos.x < 0 ) {
                add(Direction.LEFT)
            }
            if (parentPos.y - knotPos.y > 0 ) {
                add(Direction.UP)
            }
            if (parentPos.y - knotPos.y < 0 ) {
                add(Direction.DOWN)
            }
        }
    }

}