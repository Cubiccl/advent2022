package net.cubiccl.advent2022.day7.commands

import net.cubiccl.advent2022.NoSolutionException
import net.cubiccl.advent2022.day7.ElfTerminalReader

abstract class AbstractCommand(private val name: String) {

    open fun processLine(line: String, reader: ElfTerminalReader) {
        throw UnsupportedOperationException("Command $name doesn't process individual lines")
    }

    abstract fun onCalled(line: String, reader: ElfTerminalReader): AbstractCommand?

    companion object {
        fun find(name: String): AbstractCommand {
            return when (name) {
                "cd" -> ChangeDirectoryCommand()
                "ls" -> ListCommand()
                else -> throw NoSolutionException("Unknown command $name")
            }
        }
    }
}