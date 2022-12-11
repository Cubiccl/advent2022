package net.cubiccl.advent2022.day11.operation

import net.cubiccl.advent2022.day11.MonkeyItem

class MonkeyOperation(
    private val first: MonkeyOperationSource,
    private val second: MonkeyOperationSource,
    private val operand: MonkeyOperationOperand
) {

    fun execute(item: MonkeyItem) {
        val secondValue = second.get(item)
        item.worryLevel = operand.compute(first.get(item), secondValue)
        println("    Worry level is ${operand.describe()} by $secondValue to ${item.worryLevel}.")
    }

}
