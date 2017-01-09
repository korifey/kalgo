package org.korifey.kalgo.gcj.base

import org.junit.Test
import org.korifey.kalgo.facebook.y2017.qual.ProgressPie


class Runner {
    val problem : GcjBase = ProgressPie()

    @Test
    fun doGenerate() {
        problem.generate()
    }

    @Test
    fun test() {
        problem.solve(GcjInputKind.test)
    }

    @Test
    fun generated() {
        problem.verbose = false
        problem.solve(GcjInputKind.generated)
    }


    @Test
    fun small() {
        problem.solve(GcjInputKind.small)
    }

    @Test
    fun large() {
        problem.solve(GcjInputKind.large)
    }

}