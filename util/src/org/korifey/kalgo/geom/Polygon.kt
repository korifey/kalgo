package org.korifey.kalgo.geom

import java.util.*

import kotlin.comparisons.compareBy

/**
 * [vertices] in [Polygon] are in counter clockwise order and first vertex is leftmost-bottom
 */
class VertexPolygon private constructor(val vertices: MutableList<V2>)  {
    companion object {
        fun fromVertices(vertices: List<V2>) : VertexPolygon {
            val lbIndex = vertices.withIndex().minWith(compareBy({it.value.x}, {it.value.y})) ?.index ?: error("vertices are empty")
            val n = vertices.size
            val fst  = vertices[lbIndex]
            val prev = vertices[(lbIndex - 1 + n) % n]
            val nxt  = vertices[(lbIndex + 1) % n]

            val head = vertices.slice(0 until lbIndex)
            val tail = vertices.slice(lbIndex + 1 until n)


            val res = ArrayList<V2>(n)
            res.add(fst)

            if ((nxt - fst) cross (prev - fst) > 0)
            {
                res.addAll(tail)
                res.addAll(head)
            } else {
                res.addAll(head)
                res.addAll(tail)
            }

            return VertexPolygon(res)
        }
    }
}

