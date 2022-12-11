package net.cubiccl.advent2022.day11

import net.cubiccl.advent2022.NoSolutionException

class MonkeyGroup {

    private val monkeys = mutableMapOf<Int, Monkey>()
    private var commonDivider = 0L

    fun getMonkey(monkeyId: Int): Monkey {
        return monkeys[monkeyId] ?: throw NoSolutionException("Unknown monkey #$monkeyId")
    }

    fun runRounds(rounds: Int): Long {
        for (round in 1..rounds) {
            runRound()
        }
        return monkeys.values.sortedBy { -it.getInspectedItems() }.subList(0, 2).map { it.getInspectedItems() }
            .reduce { acc, i -> acc * i }
    }

    private fun runRound() {
        monkeys.toSortedMap().forEach { it.value.runTurn(this) }
        // Keep worry level down!
        monkeys.values.forEach { monkey -> monkey.getItems().forEach { it.worryLevel %= commonDivider } }
    }

    fun read(stream: Iterator<String>) {
        while (stream.hasNext()) {
            val monkeyLine = stream.next()
            val newMonkey = Monkey(monkeyLine.substring(7, monkeyLine.indexOf(':')).toInt())
            monkeys[newMonkey.id] = newMonkey
            newMonkey.readData(stream)
        }
        commonDivider = monkeys.values.stream().map { it.getDivisible() }.reduce { t, u -> t * u }.get()
    }
}
