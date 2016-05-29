package org.korifey.kalgo.util

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

    operator fun get(idx: Int) = array[idx]
    operator fun set(idx: Int, value: Boolean) {
        if (value != array[idx]) {
            count += if (value) 1 else -1
            array[idx] = value
        }
    }

}