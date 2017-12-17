package org.korifey.kalgo.geom

data class Line(val start: V2, val dir: V2) : Curve {
    override fun bbox(): Rectangle {
        if (isPoint) return Rectangle(start, V2.O)
        else if (dir.isColinear(V2.X)) return Rectangle(V2(Double.NEGATIVE_INFINITY, start.y), V2(Double.POSITIVE_INFINITY, 0))
        else if (dir.isColinear(V2.Y)) return Rectangle(V2(start.x, Double.NEGATIVE_INFINITY), V2(0, Double.POSITIVE_INFINITY))
        else return Rectangle(V2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), V2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))
    }

    val isPoint : Boolean get() = dir eq V2.O

    override fun length() = if (isPoint) 0.0 else Double.POSITIVE_INFINITY

    override fun contains(v: V2) =
            if (isPoint)
                start eq v
            else
                dir cross (v - start) eq 0.0
}