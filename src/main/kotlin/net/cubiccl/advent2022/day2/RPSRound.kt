package net.cubiccl.advent2022.day2

import net.cubiccl.advent2020.NoSolutionException

class RPSRound(private val opponent: HandShape, private val player: HandShape) {

    fun score(): Long {
        return player.score + player.outcomeValue(opponent)
    }

    companion object {
        fun parse(raw: String): RPSRound {
            return RPSRound(HandShape.getFromOpponent(raw[0]), HandShape.getFromPlayer(raw[2]))
        }

        fun parsePartTwo(raw: String): RPSRound {
            val opponent = HandShape.getFromOpponent(raw[0])
            val player: HandShape
            when (raw[2]) {
                'X' -> player = HandShape.getLoserAgainst(opponent)
                'Y' -> player = opponent
                'Z' -> player = HandShape.getWinnerAgainst(opponent)
                else -> throw NoSolutionException("Unknown condition ${raw[2]}")
            }
            return RPSRound(opponent, player)
        }
    }
}