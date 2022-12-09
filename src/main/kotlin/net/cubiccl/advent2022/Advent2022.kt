package net.cubiccl.advent2022

import net.cubiccl.advent2020.NoSolutionException
import net.cubiccl.advent2022.day1.Day1
import net.cubiccl.advent2022.day2.Day2
import net.cubiccl.advent2022.day3.Day3
import net.cubiccl.advent2022.day4.Day4
import net.cubiccl.advent2022.day5.Day5
import net.cubiccl.advent2022.day6.Day6
import net.cubiccl.advent2022.day7.Day7
import net.cubiccl.advent2022.day8.Day8
import net.cubiccl.advent2022.day9.Day9

class Advent2022 {
    fun main(args: Array<String>) {

        isTest = args.firstOrNull().toBoolean()
        isSecondPart = args[1].toBoolean()
        println("Solution is: ${this.play(9)}")
    }

    private fun play(day: Int): String {
        return getDay(day).play()
    }

    private fun getDay(dayId: Int): AbstractDay {
        return when (dayId) {
            1 -> Day1()
            2 -> Day2()
            3 -> Day3()
            4 -> Day4()
            5 -> Day5()
            6 -> Day6()
            7 -> Day7()
            8 -> Day8()
            9 -> Day9()
            else -> throw NoSolutionException("Unknown day $dayId")
        }
    }

    companion object {
        var isSecondPart = false;
        var isTest = false;
        val isFirstPart: Boolean
            get() {
                return !isSecondPart
            }
    }
}