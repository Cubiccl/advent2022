package net.cubiccl.advent2022.day10

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day10 : AbstractDay(10) {

    override fun play(): String {
        val instructions = readPuzzleInput().getLinesAs { ClockCircuitInstruction.parse(it) }
        val circuit = ClockCircuit()
        val imageBuilder = java.lang.StringBuilder()
        instructions.forEach {
            circuit.waitFor(it, imageBuilder)
            it.execute(circuit)
        }
        if (isFirstPart) {
            println(circuit.notableCycles)
            return circuit.notableCycles.values.sum().toString()
        }
        println(imageBuilder)
        return "Read above image"
    }

}
