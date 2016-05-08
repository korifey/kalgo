package org.korifey.kalgo.util

class BoolSet (count: Int, val array: BooleanArray) : Iterable<Int> {
    override fun iterator() = object:Iterator<Int> {
        var cnt = -1
        override fun hasNext(): Boolean = cnt < count

        override fun next(): Int {
            throw UnsupportedOperationException()
        }
    }

    var count : Int = count
        get
        private set

    constructor(array: BooleanArray) : this(array.count { it }, array)

    fun get(idx: Int) = array[idx]
    fun set(idx: Int, value: Boolean) {
        if (value != array[idx]) {
            count += if (value) 1 else -1
            array[idx] = value
        }
    }

}