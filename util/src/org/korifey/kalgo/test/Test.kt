package org.korifey.kalgo.test

private fun String.nextNameVersion() = split("_").let {
    it.dropLast(1).joinToString(separator = "_", postfix = "_${it.last().toInt() + 1}")
}

fun main(args: Array<String>) {
    println("aaaa_19".nextNameVersion())
}
