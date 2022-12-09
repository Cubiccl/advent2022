package net.cubiccl.advent2022

import net.cubiccl.advent2022.Advent2022.Companion.isTest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

class PuzzleInput(private val id: String, private val day: AbstractDay) {

    private val path: String
        get() {
            return "/day" + day.id + "/" + (if (isTest) "test/" else "") + id + ".txt"
        }

    private fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(this::class.java.getResource(path).openStream()))
    }

    fun getLines(): List<String> {
        return getReader().readLines()
    }

    fun <T> getLinesAs(function: (String) -> T): List<T> {
        return getLines().stream().map(function).collect(Collectors.toList())
    }

    fun getLinesAsInt(): List<Int> {
        return getLinesAs(String::toInt)
    }

    fun getLinesAsLong(): List<Long> {
        return getLinesAs(String::toLong)
    }
}