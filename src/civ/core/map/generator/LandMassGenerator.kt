package civ.core.map.generator

import civ.core.map.map.Config
import civ.core.map.map.LandMass
import civ.core.map.tiles.LandForm
import kotlin.random.Random

class LandMassGenerator(private val seed: Long) {
    fun generate(config: Config): Array<Array<LandForm>> {
        val rand = Random(seed)
        val landWaterRatio = when (config.landMass) {
            LandMass.SMALL -> 0.3
            LandMass.NORMAL -> 0.5
            LandMass.LARGE -> 0.8
        }

        val land = Array(config.size.width) {
            Array(config.size.height) {
                if (rand.nextDouble() > landWaterRatio){
                    LandForm.WATER
                } else {
                    LandForm.LAND
                }
            }
        }
        return land
    }
}
