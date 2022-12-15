package net.cubiccl.advent2022.day13

import java.util.*

data class DistressPacket(private val content: DistressPacketItem.ItemList) {

    fun compare(other: DistressPacket): Int {
        val result = DistressPacketItem.compareItem(this.content, other.content)
        println("While comparing $this and $other:")
        return (result.value?.let { if (it) -1 else 1 } ?: 0).also { println(it.toString() + "\n") }
    }

    fun isInRightOrder(other: DistressPacket): Boolean {
        return compare(other) != 1
    }

    override fun toString(): String {
        return content.toString()
    }

    companion object {
        fun parse(line: String): DistressPacket {
            val builder = ArrayDeque<MutableList<DistressPacketItem>>()
            var currentString = ""
            fun addValueIfNecessary() {
                if (currentString != "") {
                    builder.peek().add(DistressPacketItem.Value(currentString.toInt()))
                    currentString = ""
                }
            }
            line.iterator().forEachRemaining {
                when (it) {
                    '[' -> {
                        builder.push(mutableListOf())
                    }
                    ']' -> {
                        addValueIfNecessary()
                        if (builder.size > 1) {
                            val finishedList = builder.pop()
                            builder.peek().add(DistressPacketItem.ItemList(finishedList))
                        } // else we're done, keep it in memory for the return clause
                    }
                    ',' -> {
                        addValueIfNecessary()
                    }
                    else -> currentString += it
                }
                //println("$it -> $builder")
            }
            return DistressPacket(DistressPacketItem.ItemList(builder.pop()))
        }
    }

}
