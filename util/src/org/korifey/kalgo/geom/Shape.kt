package org.korifey.kalgo.geom

import java.lang.Math.abs


interface Shape<out TCurve : Curve> : GeomObject {
    //oriented
    fun oarea() : Double

    fun area() = abs(oarea())

    fun border() : TCurve

    fun perimeter() = border().length()
}

interface GeomObject {
    fun contains(v: V2) : Boolean
    fun bbox() : Rectangle
}

interface Curve : GeomObject {
    fun length() : Double
}

class Segments(val vertices: List<V2>, closed : Boolean) : Curve, List<V2> by vertices {
    override fun bbox(): Rectangle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val segmentCount = vertices.size - if (closed) 1 else 0
    private val segments: List<Segment> = List(segmentCount) { i ->
        Segment(vertices[i], vertices[(i+1) % segmentCount])
    }

    override fun length(): Double = segments.sumByDouble { it.length() }
}

interface Polygon: Shape<Segments> {
    val vertices : List<V2> get() = border().vertices
    val segments : List<V2> get() = border().vertices
}

class Rectangle(val leftBottom: V2, val size: V2) : Polygon {

    companion object {
        fun fromLeftBottomAndRightTop(leftbottom: V2, righttop: V2) : Rectangle {
            require(righttop.x gte leftbottom.x)
            require(righttop.y gte leftbottom.y)

            return Rectangle(leftbottom, righttop - leftbottom)
        }

        fun fromTwoDiagonalCorners(corner1: V2, corner2: V2) : Rectangle {
            val leftBottom = V2(minOf(corner1.x, corner2.x), minOf(corner1.y, corner2.y))
            val rightTop = V2(maxOf(corner1.x, corner2.x), maxOf(corner1.y, corner2.y))

            return fromLeftBottomAndRightTop(leftBottom, rightTop)
        }
    }

    init {
        require(size.x gte 0.0)
        require(size.y gte 0.0)
    }

    val rightTop: V2 get() = leftBottom + size

    override fun oarea(): Double = size.x * size.y

    override fun contains(v: V2): Boolean =
            (v.x - leftBottom.x).let { it gte 0.0 && it lte size.x } &&
            (v.y - leftBottom.y).let { it gte 0.0 && it lte size.y }

    override fun border() = Segments(listOf(
            leftBottom,
            leftBottom + V2(size.x, 0),
            leftBottom + size,
            leftBottom + V2(0, size.y)
    ), closed = true)

    override fun bbox() = this

    //optimization
    override fun perimeter() = 2 * (size.x + size.y)

    fun unionBbox(o: Rectangle) {

    }
}