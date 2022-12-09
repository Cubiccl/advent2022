package net.cubiccl.advent2022.day6

import net.cubiccl.advent2020.NoSolutionException
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class ElfBuffer(private val content: String) {

    fun getStartOfPacketMarker(): Int {
        val markerSize = if (isFirstPart) 4 else 14

        for (currentChar in markerSize .. content.length) {
            if (content.substring(currentChar - markerSize, currentChar).toSet().size == markerSize) {
                return currentChar
            }
        }
        throw NoSolutionException("Buffer has no start of packet marker: $content")
    }

}
