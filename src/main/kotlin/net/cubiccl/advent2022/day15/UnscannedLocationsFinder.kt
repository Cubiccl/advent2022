package net.cubiccl.advent2022.day15

import net.cubiccl.advent2022.geometry.Position

class UnscannedLocationsFinder(private val sensors: List<CaveSensor>) {

    private var noBeacons = mutableListOf<Position>()

    fun search(xRange: IntRange, yRange: IntRange): List<Position> {
        noBeacons.clear()
        xRange.forEach { x ->
            yRange.forEach { y ->
                println("$x, $y")
                checkLocation(Position(x, y))
            }
        }
        //println(noBeacons)
        return noBeacons
    }

    private fun checkLocation(position: Position) {
        val inRange = sensors.filter { it.isInRange(position) }
        // If a sensor is in range but none in range have a beacon at this position
        if (inRange.isNotEmpty() && !inRange.any { it.closestBeacon == position }) {
            // Then we scanned a position that has no beacons
            noBeacons.add(position)
            //println("$position has no beacons")
        }
    }

}
