package net.cubiccl.advent2022.day7

class ElfDirectory(name: String, val contents: MutableList<ElfFile> = mutableListOf()): ElfFile(name, 0) {

    fun listDirectories(): List<ElfDirectory> {
        return contents.filterIsInstance<ElfDirectory>().map { it.listDirectories() }.flatten().toMutableList().also { it.add(this) }
    }

    fun getDirectory(directoryName: String): ElfDirectory? {
        return contents.filterIsInstance<ElfDirectory>().firstOrNull { it.name == directoryName }
    }

    override val size: Long
        get() {
            return contents.sumOf { it.size }
        }

}
