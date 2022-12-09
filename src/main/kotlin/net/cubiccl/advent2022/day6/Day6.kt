package net.cubiccl.advent2022.day6

import net.cubiccl.advent2022.AbstractDay

class Day6 : AbstractDay(6) {
    override fun play(): String {
        val packets = readPuzzleInput().getLinesAs { ElfBuffer(it) }
        return packets.map { it.getStartOfPacketMarker() }.joinToString("-")
    }

}
