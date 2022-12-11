package net.cubiccl.advent2022.day11.operation

sealed interface MonkeyOperationOperand {
    fun compute(first: Long, second: Long): Long
    fun describe(): String

    object Add: MonkeyOperationOperand {
        override fun compute(first: Long, second: Long): Long {
            return first + second
        }
        override fun describe(): String {
            return "increased"
        }
    }

    object Multiply: MonkeyOperationOperand {
        override fun compute(first: Long, second: Long): Long {
            return first * second
        }
        override fun describe(): String {
            return "multiplied"
        }
    }
}
