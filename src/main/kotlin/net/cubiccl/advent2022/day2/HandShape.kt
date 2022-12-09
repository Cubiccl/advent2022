package net.cubiccl.advent2022.day2

import net.cubiccl.advent2020.NoSolutionException

enum class HandShape(val score: Long, val opponentId: Char, val playerId: Char) {
    ROCK(1, 'A', 'X'),
    PAPER(2, 'B', 'Y'),
    SCISSORS(3, 'C', 'Z');

    fun outcomeValue(opponent: HandShape): Long {
        if (this == opponent) {
            return 3 // Draw
        }
        if (this.score + 1 == opponent.score || (this == SCISSORS && opponent == ROCK)) {
            return 0 // Loss
        }
        if (this.score - 1 == opponent.score || this == ROCK && opponent == SCISSORS) {
            return 6 // Win
        }
        throw NoSolutionException("Impossible configuration, player = $this, opponent = $opponent")
    }

    companion object {

        fun getFromOpponent(shapeId: Char) : HandShape {
            return values().first { it.opponentId == shapeId }
        }

        fun getFromPlayer(shapeId: Char) : HandShape {
            return values().first { it.playerId == shapeId }
        }

        fun getWinnerAgainst(opponent: HandShape): HandShape {
            return when (opponent) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }
        }

        fun getLoserAgainst(opponent: HandShape): HandShape {
            return when (opponent) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
        }

    }
}