package net.cubiccl.advent2022.day5

import java.util.*

class CrateInstruction(private val quantity: Int, private val source: Int, private val destination: Int) {

    override fun toString(): String {
        return "move $quantity from ${source + 1} to ${destination + 1}"
    }

    fun execute(stacks: List<Stack<Char>>) {
        //println(this)
        for (i in 0 until quantity) {
            //println("Moving ${stacks[source].peek()} from ${source + 1} to ${destination + 1}")
            stacks[destination].push(stacks[source].pop())
        }
    }

    fun executeKeepOrder(stacks: List<Stack<Char>>) {
        val crane = Stack<Char>()
        for (i in 0 until quantity) {
            crane.push(stacks[source].pop())
        }
        for (i in 0 until quantity) {
            stacks[destination].push(crane.pop())
        }
    }

    companion object {
        fun parse(raw: String): CrateInstruction {
            val data = raw.split("move ", " from ", " to ")
            return CrateInstruction(data[1].toInt(), data[2].toInt() - 1, data[3].toInt() - 1)
        }
    }

}
