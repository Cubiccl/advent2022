package net.cubiccl.advent2022.day11

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day11 : AbstractDay(11) {
    override fun play(): String {
        val lines = readPuzzleInput().getLines()
        val monkeys = MonkeyGroup()
        monkeys.read(lines.iterator())
        return monkeys.runRounds(if (isFirstPart) 20 else 10000).toString()
    }

}
