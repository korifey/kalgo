package p19

import input
import org.korifey.kalgo.geom.*
import java.util.ArrayDeque
import javax.print.attribute.standard.MediaSize
import kotlin.math.abs
import kotlin.random.Random

val allBasis by lazy {
    val res = mutableListOf<BasisV3I>()
    val axisList = listOf(V3I.OX, V3I.OY, V3I.OZ, -V3I.OX, -V3I.OY, -V3I.OZ)
    for (x in axisList)
        for (y in axisList) {
            val z = x cross y
            if (z != V3I.O)
                res.add(BasisV3I(x, y, z))
        }

    res.toList()
}

var cnt = 0

class Beacon {
    init {
        cnt++
    }
    var prev: Beacon = this
    fun root() : Beacon {
        return if (prev == this) this else prev.root().also { prev = it }
    }

    fun merge(other: Beacon) {
        root().prev = other.root()
    }
}
class Probe(val num: Int) {
    val beacons = mutableMapOf<V3I, Beacon>()
}


fun main(args: Array<String>) {
    val probes = mutableListOf<Probe>()
    var i = 0
    for (line in input.readLines()) {
        if (line.isBlank()) continue
        val coords = line.split(",")
        if (coords.size == 3) {
            val (x, y, z) = coords.map { it.toInt() }
            probes.lastOrNull()!!.beacons[V3I(x, y, z)] = Beacon()
            continue
        }

        require(Regex("--- scanner (\\d+) ---").matchEntire(line) != null) { line }
        probes.add(Probe(i++))
    }
    println("probes:${probes.size}")

    println(probes.flatMap { it.beacons.values }.count())



    for ((i1, p1) in probes.withIndex())
        cycle@for ((i2, p2) in probes.withIndex()) {
            if (i2<=i1)
                continue

            for (basis in allBasis) {
                for ((v1, b1) in p1.beacons)
                    for ((v2, b2) in p2.beacons) {
                        val o = v1 - v2.toAnotherBasis(basis)
                        val intersecInB2 = p2.beacons.map { it.key }.filter {
                            p1.beacons.contains(o + it.toAnotherBasis(basis))
                        }

                        if (intersecInB2.size >= 12) {
                            val intersecInB1 = intersecInB2.map { o + it.toAnotherBasis(basis) }

                            //need to check for problems
                            if (p2.beacons.map { it.key }.any {
                                    val v = o + it.toAnotherBasis(basis)
                                    !p1.beacons.contains(v) &&
                                        v.normmax() <= 1000

                                })
                                continue

                            if (p1.beacons.map { it.key }.filterNot { intersecInB1.contains(it) }.any {
                                    (o - it).normmax() <= 1000
                                })
                                continue

                            println("match ${p1.num} ${p2.num} ${intersecInB2.size}")
                            for ((v, b) in p2.beacons) {
                                p1.beacons[o + v.toAnotherBasis(basis)]?.let {
                                    cnt --
                                    b.merge(it)
                                }
                            }
                            continue@cycle
                        }

                    }
            }
        }
    println(cnt)
    println(probes.flatMap { it.beacons.values }.map { it.root() }.distinct().count())

}

