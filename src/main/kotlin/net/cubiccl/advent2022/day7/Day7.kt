package net.cubiccl.advent2022.day7

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart

class Day7 : AbstractDay(7) {
    override fun play(): String {
        val terminal = readPuzzleInput().getLines()
        val fileSystem = ElfFileSystem(ElfDirectory("/"))
        val terminalReader = ElfTerminalReader(fileSystem)
        terminal.forEach { terminalReader.readLine(it) }

        if (isFirstPart) {
            return fileSystem.findDirectories { it.size <= 100000 }.sumOf { it.size }.toString()
        }

        val availableSpace = 70000000L
        val requiredSpace = 30000000L
        val usedSpace = fileSystem.root.size
        val targetSize = usedSpace - (availableSpace - requiredSpace)
        println("Used space: $usedSpace, target size: $targetSize")
        return fileSystem.findDirectories { it.size >= targetSize }.minBy { it.size }.size.toString()
    }

}
