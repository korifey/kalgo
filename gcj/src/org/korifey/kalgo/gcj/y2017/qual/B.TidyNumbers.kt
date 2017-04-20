package org.korifey.kalgo.gcj.y2017.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {

    override fun case(scanner: Scanner, out: PrintStream) {
        val number = scanner.next().toCharArray()
        val res = CharArray(number.size)
        res[0] = number[0]
        var i = 1
        while (i < res.size) {
            res[i] = number[i]
            if (res[i] < res[i-1]) break;
            i++;
        }
        if (i < res.size) {
            i --;
            while (i > 0) {
                res[i] = res[i] - 1
                if (res[i] >= res[i-1]) break;
                i--;
            }
            if (i == 0) res[i] = res[i] - 1
            i++;
            while (i < res.size) res[i++] = '9'
        }

        var x = String(res)
        if (res[0] == '0') x = x.substring(1)
        out.print(x)
    }
}
