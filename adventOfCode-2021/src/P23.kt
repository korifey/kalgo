package p20

import input
import org.korifey.kalgo.util.toward
import java.lang.Math.abs
import java.util.*

enum class Amphipod(val stepCost: Int, val destCol: Int) {
    A(1, 0),
    B(10, 1),
    C(100, 2),
    D(1000, 3);

    companion object {
        fun from(c: Char): Amphipod? = when(c) {
            'A' -> A
            'B' -> B
            'C'-> C
            'D'->D
            '.' -> null
            else -> error("Illegal c=$c")
        }
    }
}


class Position(val row: Array<Amphipod?>, val col: Array<Array<Amphipod?>>) {

    fun checkEmpty(a: Array<Amphipod?>, p: IntProgression) : Boolean{
        for (x in p)
            if (a[x] != null)
                return false

        return true
    }

    override fun toString(): String = buildString {
        append("#############\n")
        append("#")
        for (a in row)
            append(a ?: ".")
        append("#\n")
        for (i in 0 until col[0].size) {
            if (i == 0) append("##")
            else append("  ")
            for (j in col.indices) {
                append("#")
                append(col[j][i] ?: ".")
            }
            append("#")
            if (i == 0) append("##")
            else append("  ")
            append("\n")
        }
        append("  #########  \n")
    }

    constructor(strings: Array<String>) : this(
        Array(11) { i -> Amphipod.from(strings[1][1+i])},
        Array(4) { c -> Array(strings.size - 3) { r -> Amphipod.from(strings[2+r][3+c*2])} }
    ) {
        require(strings[0] == "#############")
    }

    fun possibleNext(): Map<Position, Int> {
        val res = mutableMapOf<Position, Int>()

        fun tryAm(a: Amphipod, i: Int, traversedInitialCol: Int) {
            val initialCol = (i - 2)/2

            if (traversedInitialCol != 0) { //from col (not row), so possible to place in row
                require(i == 2 || i == 4 || i == 6 || i == 8)

                for (ii in row.indices) {
                    if (ii == 2 || ii == 4 || ii == 6 || ii == 8)
                        continue

                    if (checkEmpty(row, i toward ii)) {
                        res[Position(
                            row.clone().also { it[ii] = a },
                            col.clone().also { newCols ->
                                require(col[initialCol][traversedInitialCol-1] == a)
                                newCols[initialCol] = col[initialCol].clone().also { it[traversedInitialCol-1] = null }
                            }
                        )] = a.stepCost * (traversedInitialCol + kotlin.math.abs(ii-i))
                    }
                }
            }


            val dstI = 2 + a.destCol * 2
            if (i == dstI) return

            val p = if (traversedInitialCol != 0)
                i toward (dstI)
            else if (i < dstI) (i+1) toward (dstI)
            else (i-1) toward (dstI)

            if (!checkEmpty(row, p)) return

            for (j in col[a.destCol].indices.reversed())  {
                val x= col[a.destCol][j]
                if (x == a) continue

                if (checkEmpty(col[a.destCol], 0..j)) {
                    res[Position(
                        row.clone().also { it[i] = null }, //TODO not correct for other col
                        col.clone().also { newCols ->
                            newCols[a.destCol] = col[a.destCol].clone().also { it[j] = a }
                            if (traversedInitialCol != 0) {
                                require(col[initialCol][traversedInitialCol-1] == a)
                                newCols[initialCol] = col[initialCol].clone().also { it[traversedInitialCol-1] = null }
                            }
                        }
                    )] = a.stepCost * (traversedInitialCol + kotlin.math.abs(i - dstI) + j+1)
                }

                break
            }
        }

        for ((i,a) in row.withIndex()) {
            if (a == null) continue

            tryAm(a, i, 0)
        }

        for (i in col.indices)
            for (j in col[i].indices) {
                val a = col[i][j] ?: continue

                if (a.destCol == i && col[i].all { it == null || it == a })
                    break

                tryAm(a, 2+i*2, j+1)
                break
            }

        return res
    }



    fun estimate() : Int {
        val filled = IntArray(Amphipod.values().size)

        for (i in col.indices) {
            for (j in col[0].indices.reversed())
                if (col[i][j] == Amphipod.values()[i])
                    filled[i]++
                else
                    break
        }

        var res = 0
        for (i in col.indices) {
            var corrupted = 0
            for (j in col[0].indices.reversed()) {
                val a = col[i][j] ?: continue
                if (a == Amphipod.values()[i]) {
                    if (corrupted == 0) continue
                    else res += a.stepCost*((j+2)*2)
                } else {
                    res += a.stepCost*(j+1 + abs(2*(i-a.destCol))) + a.stepCost*(col[0].size+1-filled[a.destCol])/2
                    corrupted++
                }
            }
        }

        for ((i,a) in row.withIndex()) {
            if (a == null) continue

            res += a.stepCost*(abs(i-(2+2*a.destCol))) + a.stepCost*(col[0].size+1-filled[a.destCol])/2
        }


        return res
    }

    fun isFinal() : Boolean {
        var i = -1
        return col.all {
            i++
            it.all {a -> a == Amphipod.values()[i]}
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (!row.contentEquals(other.row)) return false
        if (!col.contentDeepEquals(other.col)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row.contentHashCode()
        result = 31 * result + col.contentDeepHashCode()
        return result
    }
}


data class X(val position: Position, val dist: Int) {
    val prio = position.estimate() + dist
}

fun main(args: Array<String>) {
    val initial = Position(input.readLines().toTypedArray())
    var best = Int.MAX_VALUE

    val q = PriorityQueue<X>(compareBy { it.prio })
    q.add(X(initial, 0))

    val shortedPath = mutableMapOf<Position, Int>()
    val handled = mutableSetOf<Position>()
    while (!q.isEmpty()) {
        val x = q.poll()
        val (current, distToCurrent) = x



        if (handled.contains(current)) continue
        handled.add(current)
//        println()
//        println(current)
//        println("dist=$distToCurrent, estimate=${current.estimate()}, sum=${x.prio}")

        if (current.isFinal()) {
            println(distToCurrent)
            return
        } else {
            for ((pp, edgeDist) in current.possibleNext()) {
                val sp = shortedPath[pp]
                val newDist = edgeDist + distToCurrent
                if (sp == null || sp > newDist) {
                    shortedPath[pp] = newDist
                    if (!handled.contains(pp))
                        q.offer(X(pp, newDist))
                }
            }
        }
    }

    println("FAILED")

}
