package org.korifey.kalgo.util

import java.util.*

class BoolSet (trueCount: Int, private val array: BooleanArray) : Iterable<Int> {
    companion object {
        fun fromArray(array: BooleanArray) : BoolSet = BoolSet(array.count { it }, array)
        fun empty(n : Int) = BoolSet(0, BooleanArray(n))
        fun full(n : Int) = BoolSet(n, BooleanArray(n, {true}))
    }

    override fun iterator() = object:Iterator<Int> {
        var cnt = 0
        override fun hasNext(): Boolean {
            while (cnt < array.size && !array[cnt]) cnt++
            return (cnt < array.size)
        }

        override fun next(): Int {
            while (!array[cnt]) cnt++
            return cnt++
        }
    }

    var count : Int = trueCount
        get
        private set

    operator fun get(idx: Int) = idx in 0..count-1 && array[idx]
    operator fun set(idx: Int, value: Boolean) {
        if (value != array[idx]) {
            count += if (value) 1 else -1
            array[idx] = value
        }
    }

    fun getInternalBooleanArray() = array
}


class CountSet<T> : Iterable<Pair<T, Int>> {
    override fun iterator(): Iterator<Pair<T, Int>> = map.asIterable().map { it.key to it.value }.iterator()

    private val map = LinkedHashMap<T, Int>()

    fun remove(t: T) = add(t, -1)
    fun add(t:T, value: Int = 1) {
        map.merge(t, value) {old, new -> (old + new)}
        if (map[t] == 0) map.remove(t)
    }
    operator fun get(t: T) = map[t]
}


fun BooleanArray.toBoolSet() = BoolSet.fromArray(this)
