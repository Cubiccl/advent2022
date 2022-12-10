package net.cubiccl.advent2022.day10

class AddInstruction(private val value: Int) : ClockCircuitInstruction(2) {

    override fun execute(circuit: ClockCircuit) {
        circuit.value += value
    }

}
