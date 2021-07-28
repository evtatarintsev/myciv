package core.map.tiles

import civ.core.map.tiles.Terrain
import kotlin.test.Test
import kotlin.test.assertSame


class TestType {
    @Test
    fun testToString() {
        assertSame("Plain", Terrain.PLAIN.toString())
    }
}
