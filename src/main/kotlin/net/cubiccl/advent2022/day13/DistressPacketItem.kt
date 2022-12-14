package net.cubiccl.advent2022.day13

import net.cubiccl.advent2022.util.Describable

sealed class DistressPacketItem {

    data class Value(val value: Int) : DistressPacketItem() {
        override fun toString(): String {
            return value.toString()
        }
    }

    data class ItemList(val items: List<DistressPacketItem>) : DistressPacketItem() {
        override fun toString(): String {
            return items.toString()
        }
    }

    companion object {
        private fun compare(first: ItemList, second: ItemList): Describable<Boolean> {
            // println("Compare $first vs $second")
            for (i in 0..first.items.lastIndex) {
                if (i > second.items.lastIndex) {
                    return Describable(false, "Right side ran out of items first (${first.items} vs ${second.items})")
                }
                compareItem(first.items[i], second.items[i]).apply { if (this.value != null) return this }
            }
            return if (first.items.size < second.items.size) return Describable(true, "$first.size <= $second.size ?")
            else Describable(null, "Equal")
        }

        fun compareItem(first: DistressPacketItem, second: DistressPacketItem): Describable<Boolean> {
            //println("Compare $first vs $second")
            if (first is Value && second is Value) {
                return if (first.value != second.value) Describable(first.value <= second.value, "$first <= $second ?")
                else Describable(null, "${first.value} == ${second.value}")
            }
            if (first is ItemList && second is ItemList) {
                return compare(first, second)
            }
            //println("Mixed types; retry comparison")
            val firstAsList = (first.takeIf { it is ItemList } ?: ItemList(listOf(first))) as ItemList
            val secondAsList = (second.takeIf { it is ItemList } ?: ItemList(listOf(second))) as ItemList
            return compare(firstAsList, secondAsList)
        }
    }
}