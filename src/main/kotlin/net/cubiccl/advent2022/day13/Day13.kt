package net.cubiccl.advent2022.day13

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day13 : AbstractDay(13) {
    override fun play(): String {
        val packets = readPuzzleInput().ignoreEmptyLines().getLinesAs(DistressPacket::parse)
        //println("Read packets:")
        //packets.forEach { println(it) }

        if (isFirstPart) {
            val pairs = (0 until packets.lastIndex step 2).map { Pair(packets[it], packets[it + 1]) }

            var sumOfIndices = 0
            pairs.forEachIndexed { index, pair ->
                println()
                println("== Pair ${index + 1} ==")
                val ok = pair.first.isInRightOrder(pair.second)
                if (ok) {
                    sumOfIndices += index + 1
                }
            }
            return sumOfIndices.toString()
        }


        val divider2 = DistressPacket(DistressPacketItem.ItemList(listOf(DistressPacketItem.ItemList(listOf(DistressPacketItem.Value(2))))))
        val divider6 = DistressPacket(DistressPacketItem.ItemList(listOf(DistressPacketItem.ItemList(listOf(DistressPacketItem.Value(6))))))

        println("============ SORTING LIST ================")
        val sortedList = buildList {
            addAll(packets)
            add(divider2)
            add(divider6)
        }.sortedWith(DistressPacket::compare)
        println(sortedList.joinToString("\n"))

        return ((sortedList.indexOf(divider2) + 1) * (sortedList.indexOf(divider6) + 1)).toString()
    }

}
