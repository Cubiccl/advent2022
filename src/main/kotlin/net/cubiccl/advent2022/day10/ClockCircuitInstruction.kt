package net.cubiccl.advent2022.day10

import net.cubiccl.advent2022.NoSolutionException

abstract class ClockCircuitInstruction(val duration: Int) {

    abstract fun execute(circuit: ClockCircuit)

    companion object {
        fun parse(line: String): ClockCircuitInstruction {
            return when (line.substringBefore(" ")) {
                "noop" -> NoopInstruction()
                "addx" -> AddInstruction(line.substringAfter(" ").toInt())
                else -> throw NoSolutionException("Unknown instruction $line")
            }
        }
    }

}
