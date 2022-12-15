package net.cubiccl.advent2022.day14

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.Advent2022.Companion.isSecondPart
import net.cubiccl.advent2022.geometry.Position
import kotlin.math.max
import kotlin.math.min

class Day14 : AbstractDay(14) {
    override fun play(): String {
        val rockPaths = readPuzzleInput().getLinesAs { it.split(" -> ") }.map { list ->
            list.map {
                val split = it.split(",")
                Position(split[0].toInt(), split[1].toInt())
            }
        }
        val cave = RockCave()
        rockPaths.forEach(cave::readPath)
        if (isSecondPart) {
            cave.addFloor()
        }
        return SandFall(cave).fillTheCave().toString()
    }

}
