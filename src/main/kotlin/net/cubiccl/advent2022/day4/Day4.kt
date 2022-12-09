package net.cubiccl.advent2022.day4

import net.cubiccl.advent2022.AbstractDay

class Day4: AbstractDay(4) {
    override fun play(): String {
        val assignments = readPuzzleInput().getLinesAs(CleaningAssignment::parse)
        return assignments.count { it.hasOverlap() }.toLong().toString()
    }

}
