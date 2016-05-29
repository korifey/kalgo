package org.korifey.kalgo.util.test.cases

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.korifey.kalgo.util.BoolSet
import org.korifey.kalgo.util.test.assertIterableEqual

class TestBoolSet {

    @Test
    fun testBasic() {
        val set = BoolSet.empty(10)
        assertEquals(0, set.count)

        set[0] = true
        assertEquals(1, set.count)

        set[0] = true //no changes
        assertEquals(1, set.count)

        set[2] = true
        set[9] = true
        assertIterableEqual(arrayOf(0, 2, 9).asIterable(), set)
    }
}
