package core.map.map.generator

import civ.core.map.generator.LandMassGenerator
import civ.core.map.map.*
import civ.core.map.tiles.LandForm
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestLandMassGenerator {
    @Test
    fun `ensure generated land has correct size`() {
        // arrange
        val width = 10
        val height = 10
        val config = Config(
            size = Size(width, height),
            landMass = LandMass.NORMAL,
            temperature = Temperature.TEMPERATE,
            climate = Climate.NORMAL,
            age = Age.AGE_1,
        )
        val generator = LandMassGenerator(0)

        // act
        val land = generator.generate(config)

        // assert
        assertEquals(width, land.size)
        land.forEach {
            assertEquals(height, it.size)
        }
    }

    private fun getLandWaterRatio(landMass: LandMass): Double {
        // arrange
        val width = 100
        val height = 100
        val config = Config(
            size = Size(width, height),
            landMass = landMass,
            temperature = Temperature.TEMPERATE,
            climate = Climate.NORMAL,
            age = Age.AGE_1,
        )
        val generator = LandMassGenerator(0)

        // act
        val land = generator.generate(config)

        // assert
        var waterCount = 0
        var landCount = 0
        land.forEach { col ->
            col.forEach {
                if (it == LandForm.WATER) {
                    waterCount++
                } else {
                    landCount++
                }
            }
        }
        return landCount / waterCount.toDouble()
    }

    @Test
    fun `for LandMass_NORMAL ratio of LAND and WATER must be roughly 1`() {
        // arrange
        val expectedRatio = 1.0
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.NORMAL)

        // assert
        assertTrue(
            abs(expectedRatio - actualRatio) < tolerance,
            "water / land ration must be $expectedRatio +-$tolerance but actual $actualRatio"
        )
    }

    @Test
    fun `for LandMass_SMALL ratio of LAND and WATER must be roughly 0_5`() {
        // arrange
        val expectedRatio = 0.5
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.SMALL)

        // assert
        assertTrue(
            abs(expectedRatio - actualRatio) < tolerance,
            "water / land ration must be $expectedRatio +-$tolerance but actual $actualRatio"
        )
    }

    @Test
    fun `for LandMass_LARGE ratio of LAND and WATER must be roughly 2`() {
        // arrange
        val expectedRatio = 2
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.LARGE)

        // assert
        assertTrue(
            abs(expectedRatio - actualRatio) < tolerance,
            "water / land ration must be $expectedRatio +-$tolerance but actual $actualRatio"
        )
    }
}
