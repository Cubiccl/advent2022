package net.cubiccl.advent2022.day15

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.Advent2022.Companion.isTest
import net.cubiccl.advent2022.NoSolutionException
import net.cubiccl.advent2022.geometry.Position

class Day15 : AbstractDay(15) {
    override fun play(): String {
        val sensors = readPuzzleInput().getLinesAs { CaveSensor.parse(it) }
        if (isFirstPart) {
            val min = Position(sensors.minOf { it.min.x }, sensors.minOf { it.min.y })
            val max = Position(sensors.maxOf { it.max.x }, sensors.maxOf { it.max.y })
            val y = if (isTest) 10 else 2000000
            println("Total range: $min -> $max")
            return UnscannedLocationsFinder(sensors).search((min.x..max.x), y..y).count().toString()
        }

        val max = if (isTest) 20 else 4000000
        val pos = Position(0, 0)
        while (pos.y <= max) {
            while (pos.x <= max) {
                sensors.firstOrNull { it.isInRange(pos) }?.let {
                    val toMove = it.range - it.position.manhattan(pos) + 1
                    pos.x += toMove
                } ?: let {
                    println("Found $pos? ${(pos.x.toLong() * max + pos.y)}")
                    return (pos.x.toLong() * max + pos.y).toString()
                }
            }
            ++pos.y
            pos.x = 0
        }
        throw NoSolutionException("No solution")
    }

}
