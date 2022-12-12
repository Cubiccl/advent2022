package net.cubiccl.advent2022.day12

import net.cubiccl.advent2022.AbstractDay
import net.cubiccl.advent2022.Advent2022.Companion.isFirstPart
import net.cubiccl.advent2022.Advent2022.Companion.isSecondPart
import net.cubiccl.advent2022.NoSolutionException
import net.cubiccl.advent2022.geometry.Graph
import net.cubiccl.advent2022.geometry.Grid

class Day12 : AbstractDay(12) {
    override fun play(): String {
        val lines = readPuzzleInput().getLines()
        val grid = Grid(lines[0].length, lines.size) { pos -> lines[pos.y][pos.x] }
        val graph = createGraph(grid)
        if (isFirstPart) {
            return graph.shortestPath(locateStart(grid), locateEnd(grid)).toString()
        }

        val distances = mutableSetOf<Long>()
        val end = locateEnd(grid)
        grid.findAll('a').map { graph.shortestPath(posToNode(it.x, it.y, grid), end) }.forEach(distances::add)
        return distances.min().toString()
    }

    private fun locateStart(grid: Grid<Char>): String {
        grid.find('S')?.run { return posToNode(this.x, this.y, grid) } ?: throw NoSolutionException("No Start!")
    }

    private fun locateEnd(grid: Grid<Char>): String {
        grid.find('E')?.run { return posToNode(this.x, this.y, grid) } ?: throw NoSolutionException("No End!")
    }

    private fun createGraph(grid: Grid<Char>): Graph {
        val graph = Graph()
        for (x in 0 until grid.width) {
            for (y in 0 until grid.height) {
                graph.addNode(posToNode(x, y, grid))
            }
        }
        for (x in 0 until grid.width) {
            for (y in 0 until grid.height) {
                addVertices(x, y, grid, graph)
            }
        }
        return graph
    }

    private fun addVertices(x: Int, y: Int, grid: Grid<Char>, graph: Graph) {
        connectIfPossible(x, y, x - 1, y, grid, graph)
        connectIfPossible(x, y, x + 1, y, grid, graph)
        connectIfPossible(x, y, x, y - 1, grid, graph)
        connectIfPossible(x, y, x, y + 1, grid, graph)
    }

    private fun connectIfPossible(x: Int, y: Int, x2: Int, y2: Int, grid: Grid<Char>, graph: Graph) {
        if (!grid.isInBounds(x, y) || !grid.isInBounds(x2, y2)) {
            return
        }
        val from = convert(grid.get(x, y))
        val to = convert(grid.get(x2, y2))
        if (from + 1 >= to) { // Destination is at most source plus 1
            //println("Connecting ${posToNode(x, y, grid)}($from) to  ${posToNode(x2, y2, grid)}($to)")
            graph.connect(posToNode(x, y, grid), posToNode(x2, y2, grid), 1)
        }
    }

    private fun convert(pos: Char): Int {
        return when (pos) {
            'S' -> 'a'
            'E' -> 'z'
            else -> pos
        }.let { Character.getNumericValue(it) }
    }

    private fun posToNode(x: Int, y: Int, grid: Grid<Char>): String {
        val elevation = grid.get(x, y)
        if (isSecondPart && elevation == 'S') {
            return "a ($x, $y"
        }
        return "$elevation ($x, $y)"
    }

}
