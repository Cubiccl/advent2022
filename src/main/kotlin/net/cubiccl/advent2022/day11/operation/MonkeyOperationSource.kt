package net.cubiccl.advent2022.day11.operation

import net.cubiccl.advent2022.day11.MonkeyItem

sealed interface MonkeyOperationSource {
    fun get(item: MonkeyItem): Long

    class Constant(private val value: Long): MonkeyOperationSource {
        override fun get(item: MonkeyItem): Long {
            return value
        }
    }

    object PreviousValue : MonkeyOperationSource {
        override fun get(item: MonkeyItem): Long {
            return item.worryLevel
        }
    }

}
