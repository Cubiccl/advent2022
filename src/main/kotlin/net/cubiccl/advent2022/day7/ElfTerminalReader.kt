package net.cubiccl.advent2022.day7

import net.cubiccl.advent2022.NoSolutionException
import net.cubiccl.advent2022.day7.commands.AbstractCommand
import java.util.*

class ElfTerminalReader(private val fileSystem: ElfFileSystem) {

    private var currentCommand: AbstractCommand? = null
    private val currentDirectory = ArrayDeque<ElfDirectory>()

    init {
        moveToRoot()
    }

    // Reader functions
    fun readLine(line: String) {
        if (line.startsWith("$")) {
            processCommand(line.substring(2))
        } else {
            currentCommand?.processLine(line, this) ?: throw NoSolutionException("No current command!")
        }
    }

    private fun processCommand(line: String) {
        currentCommand = AbstractCommand.find(line.substringBefore(" ")).onCalled(line.substringAfter(" "), this)
    }


    // Command functions
    fun moveToRoot() {
        currentDirectory.clear()
        currentDirectory.push(fileSystem.root)
    }

    fun moveUp() {
        currentDirectory.pop()
    }

    fun moveInto(directoryName: String) {
        currentDirectory.peek().getDirectory(directoryName)?.run { currentDirectory.push(this) }
            ?: throw NoSolutionException("Unknown directory $directoryName in ${currentDirectory.peek().name}")
    }

    fun currentDirectory(): ElfDirectory {
        return currentDirectory.peek()
    }

}
