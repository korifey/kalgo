//package org.korifey.kalgo.codeforces.kotlinheroes

import java.util.*
import kotlin.math.min
import kotlin.reflect.KMutableProperty0


fun exit(r: Boolean) {
    println(if (r) "YES" else "NO")
}

fun subtractMin(a: KMutableProperty0<Int>, b: KMutableProperty0<Int>) {
    val m = min(a.get(), b.get())

    a.set(a.get() - m)
    b.set(b.get() - m)
}


var a : Int = 0
var b : Int = 0
var c : Int = 0


var ax : Int = 0
var bx : Int = 0
var cx : Int = 0


fun main() {

    val scanner = Scanner(System.`in`)
    a = scanner.nextInt()
    b = scanner.nextInt()
    c = scanner.nextInt()

    ax = scanner.nextInt()
    bx = scanner.nextInt()
    cx = scanner.nextInt()


    subtractMin(::a, ::ax)

    if (a > 0)
        return exit(false)

    subtractMin(::b, ::ax)
    subtractMin(::b, ::bx)

    if (b > 0)
        return exit(false)


    subtractMin(::c, ::ax)
    subtractMin(::c, ::bx)
    subtractMin(::c, ::cx)


    if (c > 0)
        return exit(false)

    exit(true)
}