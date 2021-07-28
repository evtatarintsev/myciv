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
    fun getLandWaterRatio(landMass: LandMass): Double {
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
        land.forEach {col->
            col.forEach {
                if (it == LandForm.WATER) {
                    waterCount++
                } else {
                    landCount++
                }
            }
        }
        return  waterCount.toDouble() / landCount
    }

    @Test
    fun `for LandMass_NORMAL quantity of LAND and WATER must be roughly 0_5`() {
        // arrange
        val expectedRatio = 0.5
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.NORMAL)

        // assert
        assertTrue(tolerance <  abs(expectedRatio - actualRatio))
    }

    @Test
    fun `for LandMass_SMALL quantity of LAND and WATER must be roughly 0_3`() {
        // arrange
        val expectedRatio = 0.3
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.SMALL)

        // assert
        assertTrue(tolerance <  abs(expectedRatio - actualRatio))
    }

    @Test
    fun `for LandMass_LARGE quantity of LAND and WATER must be roughly 0_8`() {
        // arrange
        val expectedRatio = 0.8
        val tolerance = 0.05 // 5%

        // act
        val actualRatio = getLandWaterRatio(LandMass.LARGE)

        // assert
        assertTrue(tolerance <  abs(expectedRatio - actualRatio))
    }
}
