package net.cubiccl.advent2022.day3

import net.cubiccl.advent2020.NoSolutionException

class RucksackGroup(private val first: Rucksack, private val second: Rucksack, private val third: Rucksack) {

    fun getBadge(): Char {
        for (item in first.contents) {
            if (second.contents.contains(item) && third.contents.contains(item)) {
                return item
            }
        }
        throw NoSolutionException("Rucksack $first has no item in common with $second and $third")
    }

}
