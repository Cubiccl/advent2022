package net.cubiccl.advent2022.day2

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day2: AbstractDay(2) {
    override fun play(): String {
        val rounds = readPuzzleInput().getLinesAs{ if (isFirstPart) RPSRound.parse(it) else RPSRound.parsePartTwo(it) }
        return rounds.sumOf { it.score() }.toString()
    }

}
