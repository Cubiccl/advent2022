package net.cubiccl.advent2022.day10

import java.lang.StringBuilder

class ClockCircuit {
    private val lineLength = 40

    val notableCycles = mutableMapOf<Int, Int>()
    init {
        notableCycles[20] = 0
        notableCycles[60] = 0
        notableCycles[100] = 0
        notableCycles[140] = 0
        notableCycles[180] = 0
        notableCycles[220] = 0
    }

    var value = 1
    private var cycle = 0

    fun waitFor(instruction: ClockCircuitInstruction, imageBuilder: StringBuilder) {
        for (i in 1..instruction.duration) {
            ++cycle
            if (cycle in notableCycles.keys) {
                notableCycles[cycle] = value * cycle
            }
            println("Cycle: $cycle, value: $value")
            // Sprite position is current value!! this is not clear
            imageBuilder.append(if (getCurrentScreenPosition() in value - 1..value + 1) '#' else '.')
            if (cycle % lineLength == 0) {
                imageBuilder.append("\n")
            }
        }
    }

    private fun getCurrentScreenPosition(): Int {
        return (cycle - 1) % lineLength
    }

}
