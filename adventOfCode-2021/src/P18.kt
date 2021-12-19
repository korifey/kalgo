package p18

import input

sealed class Node {
    abstract fun magnitude() : Int
    abstract fun explode(level: Int): ExplodeResult?
    abstract fun propagate(value: Int, leftmost: Boolean)

    fun reduce(): Node {
        //println("reduce: $this")
        while (true) {
            if (explode(0) != null) {
                //println("after explode: $this")
                continue
            }
            if (split() != null) {
                //println("after split: $this")
                continue
            }
            break
        }
        return this
    }

    abstract fun split() : Par?
    abstract fun copy(): Node
}
class Regular(var number: Int) : Node() {
    override fun magnitude() = number
    override fun explode(level: Int): ExplodeResult? = null
    override fun propagate(value: Int, leftmost: Boolean) {
        number += value
    }

    override fun split(): Par? {
        if (number < 10)
            return null
        return Par(Regular(number / 2), Regular((number+1)/ 2))
    }

    override fun copy(): Node = Regular(number)

    override fun toString(): String = number.toString()
}

class ExplodeResult(var left : Int, var right:Int)

class Par(var left: Node, var right: Node) : Node() {

    override fun explode(level: Int) : ExplodeResult? {
        if (level == 4)
            return ExplodeResult((left as Regular).number, (right as Regular).number)
        left.explode(level+1)?.let { res ->
            if (res.right != 0)
                right.propagate(res.right, leftmost = true)
            if (level == 3)
                left = Regular(0)
            return ExplodeResult(res.left, 0)
        }
        right.explode(level+1)?.let { res ->
            if (res.left != 0)
                left.propagate(res.left, leftmost = false)
            if (level == 3)
                right = Regular(0)
            return ExplodeResult(0, res.right)
        }
        return null
    }

    override fun propagate(value: Int, leftmost: Boolean) {
        if (leftmost) left.propagate(value, leftmost)
        else right.propagate(value, leftmost)
    }

    override fun split(): Par? {
        return left.split()?.also { if (left is Regular) left = it } ?: right.split()?.also { if (right is Regular) right = it }
    }

    override fun copy(): Node = Par(left.copy(), right.copy())

    override fun magnitude() = 3 * left.magnitude() + 2 * right.magnitude()

    override fun toString(): String {
        return "[$left,$right]"
    }
}

operator fun Node.plus(other: Node) = Par(this.copy(), other.copy()).reduce()

fun parse(s: String): Node {
    var pos = 0
    fun inner() : Node = when {
        s[pos].isDigit() -> Regular(s[pos++] - '0')
        s[pos] == '[' -> {
            pos++
            val l = inner()
            require(s[pos++] == ',')
            val r = inner()
            require(s[pos++] == ']')
            Par(l, r)
        }
        else -> error("$s , pos = $pos, s[pos]=${s[pos]}")
    }
    return inner()
}

fun main(args: Array<String>) {
    val res = input.readLines().map { parse(it) }.reduce { a, b ->
        (a+b).also { println(it) }
    }
    println("result="+res.magnitude())
}

