package net.cubiccl.advent2022.util

data class Describable<T>(val value: T?, val description: String) {
    override fun toString(): String {
        return "$value ($description)"
    }
}