package net.cubiccl.advent2022.day1

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day1 : AbstractDay(1) {
    override fun play(): String {
        val calories = readPuzzleInput().getLines()
        val sums = sum(calories)
        if (isFirstPart) {
            return sums.max().toString()
        }
        return sums.sortedDescending().subList(0, 3).sum().toString()
    }

    private fun sum(calories: List<String>): List<Long> {
        val sums = mutableListOf<Long>()
        var current = 0
        calories.forEach {
            if (it == "") {
                sums.add(current.toLong())
                current = 0
            } else {
                current += it.toInt()
            }
        }
        if (current != 0) {
            sums.add(current.toLong())
        }
        return sums
    }

}
