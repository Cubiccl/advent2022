package net.cubiccl.advent2022.day3

import net.cubiccl.advent2022.NoSolutionException

class Rucksack(val contents: String) {
    private val firstCompartment: String
        get() {
            return contents.substring(0, contents.length / 2)
        }

    private val secondCompartment: String
        get() {
            return contents.substring(contents.length / 2)
        }

    fun getSharedItem(): Char {
        for (item in firstCompartment) {
            if (secondCompartment.contains(item)) {
                return item
            }
        }
        throw NoSolutionException("Rucksack with first compartment $firstCompartment and second $secondCompartment is correctly sorted")
    }

}
