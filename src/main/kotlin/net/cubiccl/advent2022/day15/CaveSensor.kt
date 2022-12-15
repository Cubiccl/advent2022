package net.cubiccl.advent2022.day15

import net.cubiccl.advent2022.geometry.Position

class CaveSensor(val position: Position, val closestBeacon: Position) {

    val range = position.manhattan(closestBeacon)
    val min = position.minus(range)
    val max = position.plus(range)

    fun isInRange(pos: Position): Boolean {
        return range >= position.manhattan(pos)
    }

    companion object {
        fun parse(line: String): CaveSensor {
            val posX = line.substringAfter("x=").substringBefore(", y=").toInt()
            val posY = line.substringAfter("y=").substringBefore(": ").toInt()
            val beaconX = line.substringAfter("is at x=").substringBefore(", y=").toInt()
            val beaconY = line.substringAfter("is at x=").substringAfter("y=").toInt()
            return CaveSensor(Position(posX, posY), Position(beaconX, beaconY))
        }
    }

}
