package net.cubiccl.advent2022.day11

import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.NoSolutionException
import net.cubiccl.advent2022.day11.operation.MonkeyOperation
import net.cubiccl.advent2022.day11.operation.MonkeyOperationOperand
import net.cubiccl.advent2022.day11.operation.MonkeyOperationSource
import java.util.*

class Monkey(val id: Int) {

    private val items = LinkedList<MonkeyItem>()
    private lateinit var operation: MonkeyOperation
    private var testDivisibleBy = 0L
    private var throwToIfTrue = 0
    private var throwToIfFalse = 0
    private var inspectedItems = 0L

    private fun addItem(item: MonkeyItem) {
        items.add(item)
    }

    private fun drawItem(): MonkeyItem {
        return items.removeFirst()
    }

    private fun test(item: MonkeyItem): Boolean {
        return item.worryLevel % testDivisibleBy == 0L
    }

    fun getInspectedItems(): Long {
        return inspectedItems
    }

    fun runTurn(group: MonkeyGroup) {
        println("Monkey $id:")
        while (items.isNotEmpty()) {
            val currentItem = drawItem()
            println("  Monkey inspects an item with a worry level of ${currentItem.worryLevel}.")
            ++inspectedItems
            operation.execute(currentItem)
            if (isFirstPart) {
                currentItem.worryLevel /= 3
                println("    Monkey gets bored with item. Worry level is divided by 3 to ${currentItem.worryLevel}.")
            }
            val throwTo = if (test(currentItem)) throwToIfTrue else throwToIfFalse
            println("    Item with worry level ${currentItem.worryLevel} is thrown to monkey $throwTo.")
            group.getMonkey(throwTo).addItem(currentItem)
        }
    }

    fun readData(stream: Iterator<String>) {
        readStartingItems(stream.next())
        readOperation(stream.next())
        readTest(stream.next())
        readTrue(stream.next())
        readFalse(stream.next())
        if (stream.hasNext()) stream.next() // Read empty line
    }

    private fun readStartingItems(line: String) {
        line.substringAfter("items: ").split(", ").map { MonkeyItem(it.toLong()) }.forEach(items::add)
    }

    private fun readOperation(line: String) {
        val contents = line.substringAfter("new = ").split(" ")
        val first = contents[0].let {
            if (it == "old") {
                MonkeyOperationSource.PreviousValue
            } else {
                MonkeyOperationSource.Constant(it.toLong())
            }
        }
        val second = contents[2].let {
            if (it == "old") {
                MonkeyOperationSource.PreviousValue
            } else {
                MonkeyOperationSource.Constant(it.toLong())
            }
        }
        val operand = contents[1].let {
            when (it) {
                "+" -> MonkeyOperationOperand.Add
                "*" -> MonkeyOperationOperand.Multiply
                else -> throw NoSolutionException("Unknown operand $it")
            }
        }
        operation = MonkeyOperation(first, second, operand)
    }

    private fun readTest(line: String) {
        testDivisibleBy = line.substringAfter(" by ").toLong()
    }

    private fun readTrue(line: String) {
        throwToIfTrue = line.substringAfter("monkey ").toInt()
    }

    private fun readFalse(line: String) {
        throwToIfFalse = line.substringAfter("monkey ").toInt()
    }

    fun getItems(): List<MonkeyItem> {
        return items
    }

    fun getDivisible(): Long {
        return testDivisibleBy
    }

}
