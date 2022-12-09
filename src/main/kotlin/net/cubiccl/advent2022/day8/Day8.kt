package net.cubiccl.advent2022.day8

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.geometry.Grid

class Day8 : AbstractDay(8) {

    override fun play(): String {
        val lines = readPuzzleInput().getLines()
        val treeterator = Treeterator(Grid(lines[0].length, lines.size) { lines[it.x][it.y].digitToInt() })
        if (isFirstPart) {
            treeterator.findAllVisibleTrees()
            return treeterator.visibleTrees.size.toString()
        }
        return treeterator.findBestScenery().toString()
    }

}
