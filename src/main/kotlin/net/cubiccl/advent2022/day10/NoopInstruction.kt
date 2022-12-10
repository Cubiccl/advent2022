package net.cubiccl.advent2022.day10

class NoopInstruction: ClockCircuitInstruction(1) {
    override fun execute(circuit: ClockCircuit) {}
}
