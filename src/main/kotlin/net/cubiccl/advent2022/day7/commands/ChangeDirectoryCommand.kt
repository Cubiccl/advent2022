package net.cubiccl.advent2022.day7.commands

import net.cubiccl.advent2022.day7.ElfTerminalReader

class ChangeDirectoryCommand : AbstractCommand("cd") {

    override fun onCalled(line: String, reader: ElfTerminalReader): AbstractCommand? {
        if (line.startsWith("/")) {
            reader.moveToRoot()
        } else if (line.startsWith("..")) {
            reader.moveUp()
        } else {
            reader.moveInto(line)
        }
        return null
    }

}
