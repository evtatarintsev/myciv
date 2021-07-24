package core.map.tiles

import civ.core.map.tiles.Type
import kotlin.test.Test
import kotlin.test.assertSame


class TestType {
    @Test
    fun testToString() {
        assertSame("Plain", Type.PLAIN.toString())
    }
}
