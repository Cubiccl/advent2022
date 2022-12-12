package net.cubiccl.advent2022.geometry

import net.cubiccl.advent2022.NoSolutionException

class Graph {

    private val nodes = mutableSetOf<String>()
    private val vertices = mutableMapOf<Pair<String, String>, Int>()

    fun addNode(node: String) {
        if (nodes.contains(node)) {
            throw NoSolutionException("Node $node already registered")
        }
        nodes.add(node)
    }

    fun connect(fromNode: String, toNode: String, distance: Int) {
        vertices[Pair(fromNode, toNode)] = distance
    }

    fun reachable(fromNode: String, toNode: String): Boolean {
        return vertices.containsKey(Pair(fromNode, toNode))
    }

    fun distance(fromNode: String, toNode: String): Int? {
        return vertices[Pair(fromNode, toNode)]
    }

    private fun getNeighbors(node: String): List<String> {
        return vertices.keys.filter { it.first == node }.map { it.second }
    }

    fun shortestPath(fromNode: String, toNode: String): Long {
        println("Computing shortest path from $fromNode to $toNode")
        return Dijkstra(this).shortestPath(fromNode, toNode)
    }

    class Dijkstra(private val graph: Graph) {
        private val unvisited = mutableSetOf<String>()
        private val tentativeDistance = mutableMapOf<String, Long>()
        private val parents = mutableMapOf<String, String>()
        private lateinit var currentNode: String
        private lateinit var destination: String

        init {
            graph.nodes.forEach {
                unvisited.add(it)
                tentativeDistance[it] = Long.MAX_VALUE
            }
        }

        /**
         * !!! Not shortest distance !!!
         */
        fun shortestPath(fromNode: String, toNode: String): Long {
            tentativeDistance[fromNode] = 0
            currentNode = fromNode
            destination = toNode
            do {
                // println("Current: $currentNode")
                iterate()
            } while (shouldContinue())
            // println("Stopping at: $currentNode")
            return getTentativeDistance(destination)
        }

        private fun iterate() {
            graph.getNeighbors(currentNode).forEach {
                // println("Evaluating tentative distance of $it from $currentNode")
                val fromTentative = getTentativeDistance(currentNode)
                val toTentative = graph.distance(currentNode, it) ?: run { throw NoSolutionException("Unreachable state") }
                val previousTentative = getTentativeDistance(it)
                val newTentative = fromTentative + toTentative
                if (previousTentative > newTentative) {
                    // println("New distance found: $newTentative (previous was $previousTentative)")
                    tentativeDistance[it] = newTentative
                    parents[it] = currentNode
                }
            }
            unvisited.remove(currentNode)
            currentNode = unvisited.minByOrNull { getTentativeDistance(it) } ?: destination
        }

        private fun shouldContinue(): Boolean {
            return unvisited.contains(destination) && unvisited.minOfOrNull { getTentativeDistance(it) } != Long.MAX_VALUE
        }

        private fun getTentativeDistance(node: String): Long {
            return tentativeDistance[node] ?: run { throw NoSolutionException("Unreachable state") }
        }
    }

}