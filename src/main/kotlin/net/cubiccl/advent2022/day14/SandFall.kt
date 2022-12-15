package net.cubiccl.advent2022.day14

import net.cubiccl.advent2022.Advent2022.Companion.isSecondPart
import net.cubiccl.advent2022.geometry.Position

class SandFall(private val cave: RockCave) {

    private val maxY = cave.findMaxY()

    fun fillTheCave(): Int {
        var sandUnitsFallen = 0
        while (makeSandFall()) {
            ++sandUnitsFallen
        }
        return sandUnitsFallen.let { if (isSecondPart) it + 1 else it }
    }

    private fun makeSandFall(): Boolean {
        val sandPos = Position(500, 0)
        var changed: Boolean
        do {
            changed = tryToMoveTo(sandPos) { ++it.y; it }
            if (!changed) {
                changed = tryToMoveTo(sandPos) { ++it.y; --it.x; it }
            }
            if (!changed) {
                changed = tryToMoveTo(sandPos) { ++it.y; ++it.x; it }
            }
        } while (changed && sandPos.y <= maxY)
        if (!changed) {
            cave.set(sandPos, RockCave.RockCaveTile.SAND)
            println("Sand landed at $sandPos")
        }
        var stopping = false
        if (changed) {
            stopping = true
            //println("We are stopping because sand is falling in the void at $sandPos")
        } else if (sandPos == Position(500, 0)) {
            stopping = true
            //println("We are stopping because sand has reached $sandPos")
        }
        return !stopping
        // If it changed then it didn't find a position to not change then it didn't find ground
    }

    private fun tryToMoveTo(sandPos: Position, movement: (Position) -> Position): Boolean {
        return (cave.get(movement.invoke(sandPos.clone())) == RockCave.RockCaveTile.AIR).also {
            //println(cave.get(movement.invoke(sandPos.clone())))
            if (it) {
                movement.invoke(sandPos)
                //println("Sand moved to $sandPos")
            }
        }
    }

}
