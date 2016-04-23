package org.korifey.kalgo.gcj.base

import java.io.*
import java.nio.file.Path
import java.util.*
import kotlin.system.measureTimeMillis

enum class GcjInputKind {
    test,
    generated,
    small,
    large
}

abstract class GcjBase {

    var verbose = true
    val prefix: String
    init {
        prefix = this.javaClass.run {
            "src/" +
            "${`package`.name.replace('.','/')}" +
            "/$simpleName"
        }
    }



    protected abstract fun case(scanner: Scanner, out: PrintStream)
    protected open fun generate(out: PrintStream) {}

    fun solve(inputKind: GcjInputKind) {
        val basename = "${this.prefix}-${inputKind.name}"

        val inputFile = File(basename+".in")
        println("Input: $inputFile")
        if (!inputFile.exists()) throw IllegalArgumentException("Input file doesn't exist")

        val outputFile = File(basename+".out")
        println("Output: $outputFile")


        PrintStream(BufferedOutputStream(FileOutputStream(outputFile))).use { out ->
            BufferedInputStream(FileInputStream(inputFile)).use {
                val scanner = Scanner(it)

                val n = scanner.nextInt();
                println()
                println("$n cases")

                val totalTime = measureTimeMillis {
                    for (i in 1..n) {
                        out.print("Case #$i: ")
                        val caseTime = measureTimeMillis { case(scanner, out) }
                        out.println()

                        if (verbose) println("Case $i: solved in ${caseTime} ms")
                    }
                }
                println("Total time: ${totalTime} ms")
            }
        }
    }


    fun generate() {
        val kind = GcjInputKind.generated
        val basename = "${this.prefix}-${kind.name}"
        val file = File(basename+".in")

        println("Generate into: $file")

        PrintStream(BufferedOutputStream(FileOutputStream(file))).use { out ->
            generate(out)
        }
    }
}

fun Scanner.nextIntArray(n: Int) : IntArray {
    var res = IntArray(n)
    for (i in 0..n-1) res[i] = nextInt()
    return res
}

fun Scanner.nextIntArray() : IntArray {
    val scanner = Scanner(next())
    var res = ArrayList<Int>()
    while (scanner.hasNextInt()) res.add(scanner.nextInt())
    return res.toIntArray()
}

