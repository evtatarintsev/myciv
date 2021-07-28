package core.map.map

import civ.core.map.map.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TestConfig {
    @Test
    fun testDefaultConfig() {
        // arrange
        val expectedDefaultConfig = Config(
            size = Size(600, 400),
            landMass = LandMass.NORMAL,
            temperature = Temperature.TEMPERATE,
            climate = Climate.NORMAL,
            age = Age.AGE_1,
        )

        // act / assert
        assertEquals(expectedDefaultConfig, DefaultConfig)
    }
}
