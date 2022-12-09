package net.cubiccl.advent2022.day7.commands

import net.cubiccl.advent2022.day7.ElfDirectory
import net.cubiccl.advent2022.day7.ElfFile
import net.cubiccl.advent2022.day7.ElfTerminalReader

class ListCommand : AbstractCommand("ls") {

    override fun onCalled(line: String, reader: ElfTerminalReader): AbstractCommand {
        println("Listing contents of ${reader.currentDirectory()}")
        return this
    }

    override fun processLine(line: String, reader: ElfTerminalReader) {
        reader.currentDirectory().contents.add(readFile(line))
    }

    private fun readFile(line: String): ElfFile {
        if (line.startsWith("dir")) {
            return ElfDirectory(line.substringAfter("dir "))
        }
        return ElfFile(line.substringAfter(" "), line.substringBefore(" ").toLong())
    }

}
