package p13

import input

data class P(val x: Int, val y: Int) {
    fun reflectVerticalAxis(axis: Int): P {
        require(x != axis)
        return if (x < axis) this else P(axis - (x - axis), y)
    }

    fun reflectHorizontalAxis(axis: Int): P {
        require(y != axis)
        return if (y < axis) this else P(x, axis - (y-axis))
    }

}
fun main(args: Array<String>) {
    var set = mutableSetOf<P>()
    var readFold = false


    for (line in input.readLines()) {
        if (line.isBlank())
            readFold = true
        else if (readFold) {
            val (axisKind, axisValue) = line.removePrefix("fold along ").split("=")
            val newSet = mutableSetOf<P>()
            set.mapTo(newSet) { p ->

                if (axisKind == "x")
                    p.reflectVerticalAxis(axisValue.toInt())
                else {
                    require(axisKind == "y") {axisKind}
                    p.reflectHorizontalAxis(axisValue.toInt())
                }
            }
            set = newSet
            println(set.size)
            return
        } else {
            val (x,y) = line.split(',').map { it.toInt() }
            set.add(P(x,y))
        }

    }
}

