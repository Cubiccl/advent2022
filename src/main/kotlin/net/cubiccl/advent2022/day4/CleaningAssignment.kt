package net.cubiccl.advent2022.day4

import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class CleaningAssignment(private val first: Pair<Int, Int>, private val second: Pair<Int, Int>) {

    fun hasOverlap(): Boolean {
        if (isFirstPart) {
            return contains(first, second) || contains(second, first)
        }
        return overlaps(first, second)
    }

    private fun overlaps(first: Pair<Int, Int>, second: Pair<Int, Int>): Boolean {
        return contains(first, second) || (first.first in second.first..second.second) || (first.second in second.first..second.second)
    }

    private fun contains(first: Pair<Int, Int>, second: Pair<Int, Int>): Boolean {
        return first.first <= second.first && first.second >= second.second
    }

    companion object {
        fun parse(raw: String): CleaningAssignment {
            val indices = raw.split(",", "-").map(String::toInt)
            return CleaningAssignment(Pair(indices[0], indices[1]), Pair(indices[2], indices[3]))
        }
    }

}
