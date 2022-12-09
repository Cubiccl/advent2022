package net.cubiccl.advent2022.day3

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day3 : AbstractDay(3) {
    override fun play(): String {
        val rucksacks = readPuzzleInput().getLinesAs { Rucksack(it) }
        if (isFirstPart) {
            return rucksacks.map { it.getSharedItem() }.sumOf { getItemPriority(it) }.toString()
        }
        val groups = regroup(rucksacks)
        return groups.map { it.getBadge() }.sumOf { getItemPriority(it) }.toString()
    }

    private fun regroup(rucksacks: List<Rucksack>): List<RucksackGroup> {
        val groups = mutableListOf<RucksackGroup>()
        for (i in rucksacks.indices step 3) {
            groups.add(RucksackGroup(rucksacks[i], rucksacks[i + 1], rucksacks[i + 2]))
        }
        return groups
    }

    private fun getItemPriority(item: Char): Long {
        if (item.isLowerCase()) {
            return item.code.toLong() - 96
        }
        return item.code.toLong() - 64 + 26
    }

}
