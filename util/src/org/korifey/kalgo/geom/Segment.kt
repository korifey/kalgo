package org.korifey.kalgo.geom

data class Segment(val s: V2, val e: V2) : Curve {
    
    override fun bbox() = Rectangle.fromTwoDiagonalCorners(s, e)

    override fun length() = (e-s).norm

    override fun contains(v: V2): Boolean = containingLine().contains(v) && bbox().contains(v)

    fun containingLine() = Line(s, e - s)

}