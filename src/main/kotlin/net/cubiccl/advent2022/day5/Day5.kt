package net.cubiccl.advent2022.day5

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.Advent2022.Companion.isSecondPart
import java.util.*

class Day5 : AbstractDay(5) {
    override fun play(): String {
        val data = readPuzzleInput().getLines()
        val stacks = buildList { for (i in 0 until findCrateCount(data)) add(Stack<Char>()) }
        buildStacks(data, stacks, getCountLineIndex(data) - 1)
        val instructions = data.subList(getCountLineIndex(data) + 2, data.size).map(CrateInstruction::parse)
        instructions.forEach { if (isFirstPart) it.execute(stacks) else it.executeKeepOrder(stacks) }
        return stacks.map { it.peek() }.joinToString("")
    }

    private fun buildStacks(data: List<String>, stacks: List<Stack<Char>>, index: Int) {
        if (index == -1) {
            return
        }
        val line = data[index]
        for (i in 1 until line.length step 4) {
            if (line[i] != ' ') {
                stacks[(i - 1) / 4].add(line[i])
            }
        }
        buildStacks(data, stacks, index - 1)
    }

    private fun findCrateCount(data: List<String>): Int {
        val countLine = data[getCountLineIndex(data)]
        return countLine.split("   ").last().trim().toInt()
    }

    private fun getCountLineIndex(data: List<String>): Int {
        return data.indexOfFirst { it[1] == '1' }
    }

}
