package net.cubiccl.advent2022.day8

import net.cubiccl.advent2022.geometry.Grid
import net.cubiccl.advent2022.geometry.Position
import kotlin.math.max

class Treeterator(private val treeGrid: Grid<Int>) {

    val visibleTrees = mutableSetOf<Position<Int>>()

    fun findAllVisibleTrees() {
        findVisible(
            0,
            0,
            { pos -> pos.x++ },
            { pos -> pos.x = 0; pos.y++ },
            { it.x == treeGrid.width },
            { it.y == treeGrid.height })

        findVisible(
            0,
            0,
            { pos -> pos.y++ },
            { pos -> pos.x++; pos.y = 0 },
            { it.y == treeGrid.height },
            { it.x == treeGrid.width })

        findVisible(
            treeGrid.maxX,
            treeGrid.maxY,
            { pos -> pos.x-- },
            { pos -> pos.x = treeGrid.maxX; pos.y-- },
            { it.x == -1 },
            { it.y == -1 })

        findVisible(
            treeGrid.maxX,
            treeGrid.maxY,
            { pos -> pos.y-- },
            { pos -> pos.x--; pos.y = treeGrid.maxY },
            { it.y == -1 },
            { it.x == -1 })
    }

    private fun findVisible(
        startX: Int,
        startY: Int,
        singleStep: (Position<Int>) -> Unit,
        lineStep: (Position<Int>) -> Unit,
        goToNextLine: (Position<Int>) -> Boolean,
        stop: (Position<Int>) -> Boolean
    ) {
        val position = Position(startX, startY)
        while (!stop.invoke(position)) {
            findVisibleForLine(position, singleStep, goToNextLine)
            lineStep.invoke(position)
        }
    }

    private fun findVisibleForLine(
        position: Position<Int>,
        singleStep: (Position<Int>) -> Unit,
        goToNextLine: (Position<Int>) -> Boolean
    ) {
        var max = -1
        while (!goToNextLine.invoke(position)) {
            val current = treeGrid.get(position)
            if (current > max) {
                println("Found tree height $current at $position")
                max = current
                visibleTrees.add(position.clone())
                println(visibleTrees.size)
            }
            singleStep.invoke(position)
            //println(position)
            //println(goToNextLine.invoke(position))
        }
    }

    fun findBestScenery(): Long {
        var bestValue = 0L
        for (x in 0..treeGrid.maxX) {
            for (y in 0..treeGrid.maxY) {
                bestValue = max(bestValue, computeScenery(Position(x, y)))
            }
        }
        return bestValue
    }

    private fun computeScenery(position: Position<Int>): Long {
        val height = treeGrid.get(position)

        var left = 0L
        for (x in position.x - 1 downTo 0) {
            ++left
            if (height <= treeGrid.get(x, position.y)) {
                break
            }
        }

        var right = 0L
        for (x in position.x + 1..treeGrid.maxX) {
            ++right
            if (height <= treeGrid.get(x, position.y)) {
                break
            }
        }

        var up = 0L
        for (y in position.y - 1 downTo 0) {
            ++up
            if (height <= treeGrid.get(position.x, y)) {
                break
            }
        }

        var down = 0L
        for (y in position.y + 1..treeGrid.maxY) {
            ++down
            if (height <= treeGrid.get(position.x, y)) {
                break
            }
        }

        return left * right * up * down
    }
}