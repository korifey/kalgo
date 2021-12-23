package p19

import input
import org.korifey.kalgo.geom.*
import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.Math.abs
import java.util.ArrayDeque

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

    fun merge(other: Beacon) : Boolean {
        if (root() == other.root()) return false

        root().prev = other.root()
        return true
    }
}

data class Fixed(val origin: V3I, val basis:BasisV3I)
class Probe(val num: Int) {
    var fixed: Fixed? = null
    val beacons = mutableMapOf<V3I, Beacon>()

    fun beaconsInFixed(f: Fixed) = beacons.mapKeys {
            (v, _) -> f.origin + v.toAnotherBasis(f.basis)
    }

    override fun toString() = "$num"
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
    println("initial beacons: "+probes.flatMap { it.beacons.values }.count())

    probes[0].fixed = Fixed(V3I.O, allBasis[0])

    val unused = LinkedHashSet(probes)
    unused.remove(probes[0])
    val q = ArrayDeque(listOf(probes[0]))
    val processed = mutableSetOf<Pair<Int, Int>>()

    while (q.isNotEmpty() || unused.isNotEmpty()) {
        if (q.isEmpty()) {
            println("new connectivity component")
            q.offer(unused.first().also { unused.remove(it) })
        }

        val p1 = q.poll()

        val (fixedOrigin, fixedBasis) = p1.fixed ?: error("Origin of $p1 is not fixed")
        println("Extracted from queue: ${p1.num}, basis=${fixedBasis}, origin =${fixedOrigin}")

        val p1BeaconsInFixed = p1.beaconsInFixed(p1.fixed!!)

        for (p2 in probes.filterNot { it == p1 || processed.contains(min(p1.num, it.num) to max(p1.num, it.num)) }) {
            processed.add(min(p1.num, p2.num) to max(p1.num, p2.num))

            //check whether p1 and p2 can scan one region

            val p2Fixeds =
                if (p2.fixed != null) {
                    sequenceOf(p2.fixed!!)
                } else sequence {
                    for (basis in allBasis) {
                        for (v1 in p1BeaconsInFixed.keys)
                            for (v2 in p2.beacons.keys) {
                                val o = v1 - v2.toAnotherBasis(basis)
                                yield(Fixed(o, basis))
                            }
                    }
                }

            for (f in p2Fixeds) {
                val p2beaconsInFixed = p2.beaconsInFixed(f)
                val intersection = p2beaconsInFixed.filter { (v, _) ->
                    p1BeaconsInFixed.contains(v)
                }

                if (intersection.size >= 12) {
                    println("Match $p1 $p2, count=${intersection.size}, $p2.fixed: ${p2.fixed} -> $f")
                    p2.fixed = f

                    //check
                    for ((v1, _) in p1BeaconsInFixed) {
                        if (intersection.containsKey(v1)) continue
                        require((p2.fixed!!.origin - v1).normmax() > 1000)
                    }
                    for ((v2, _) in p2beaconsInFixed) {
                        if (intersection.containsKey(v2)) continue
                        require((p1.fixed!!.origin - v2).normmax() > 1000)
                    }

                    //merge
                    for ((v, p2b) in intersection) {
                        p1BeaconsInFixed[v]!!.let { p1b ->
                            //cnt --

                            p2b.merge(p1b)
//                            println("$v merged="+p2b.merge(p1b))
                        }
                    }
                    if (unused.remove(p2)) {
                        q.add(p2)
                    } else {
                        println("Already USED")
                    }

                    break
                }
            }

        }
    }


//    for ((i1, p1) in probes.withIndex())
//        cycle@for ((i2, p2) in probes.withIndex()) {
//            if (i2<=i1)
//                continue
//
//            for (basis in allBasis) {
//                for ((v1, b1) in p1.beacons)
//                    for ((v2, b2) in p2.beacons) {
//                        val o = v1 - v2.toAnotherBasis(basis)
//                        val intersecInB2 = p2.beacons.map { it.key }.filter {
//                            p1.beacons.contains(o + it.toAnotherBasis(basis))
//                        }
//
//                        if (intersecInB2.size >= 12) {
//                            val intersecInB1 = intersecInB2.map { o + it.toAnotherBasis(basis) }
//
//                            //need to check for problems
//                            if (p2.beacons.map { it.key }.any {
//                                    val v = o + it.toAnotherBasis(basis)
//                                    !p1.beacons.contains(v) &&
//                                        v.normmax() <= 1000
//
//                                })
//                                continue
//
//                            if (p1.beacons.map { it.key }.filterNot { intersecInB1.contains(it) }.any {
//                                    (o - it).normmax() <= 1000
//                                })
//                                continue
//
//                            println("match ${p1.num} ${p2.num} ${intersecInB2.size}")
//                            for ((v, b) in p2.beacons) {
//                                p1.beacons[o + v.toAnotherBasis(basis)]?.let {
//                                    cnt --
//                                    b.merge(it)
//                                }
//                            }
//                            continue@cycle
//                        }
//
//                    }
//            }
//        }
    //println(cnt)
    //WRONG: println(probes.flatMap { it.beacons.values }.map { it.root() }.distinct().count())
    val allBeacons = probes.flatMap { it.beaconsInFixed(it.fixed!!).keys }.distinct()
    println("p19 = "+allBeacons.count())
    println("p19x = "+probes.flatMap { p1 -> probes.map { p1.fixed!!.origin to it.fixed!!.origin } }.maxOfOrNull { (v1, v2) -> (v1-v2).normManhatten() })

}

