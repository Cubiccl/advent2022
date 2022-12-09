package net.cubiccl.advent2022.day7

class ElfFileSystem(val root: ElfDirectory) {

    fun findDirectories(filter: (ElfDirectory) -> Boolean): List<ElfDirectory> {
        return listDirectories().filter(filter)
    }

    private fun listDirectories(): List<ElfDirectory> {
        return root.listDirectories()
    }

}
