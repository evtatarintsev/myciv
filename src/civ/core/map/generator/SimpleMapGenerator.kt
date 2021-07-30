package civ.core.map.generator

import civ.core.map.map.Config
import civ.core.map.map.GameMap
import civ.core.map.tiles.LandForm
import civ.core.map.tiles.Terrain
import civ.core.map.tiles.Tile
import kotlin.random.Random

class SimpleMapGenerator(private val seed: Long) {
    private val rand = Random(seed)

    fun generate(config: Config): GameMap {
        val land = LandMassGenerator(seed).generate(config)
        val terrains = spawnTerrain(land)
        val tiles = spawnTiles(terrains)
        return GameMap(tiles)
    }

    private fun spawnTerrain(land: Array<Array<LandForm>>): Array<Array<Terrain>> {
        return land.map { col ->
            col.map {
                when (it) {
                    LandForm.WATER -> Terrain.water[rand.nextInt(Terrain.water.size)]
                    LandForm.LAND -> Terrain.lands[rand.nextInt(Terrain.lands.size)]
                }
            }.toTypedArray()
        }.toTypedArray()
    }

    private fun spawnTiles(terrains: Array<Array<Terrain>>): Array<Array<Tile>> {
        return terrains.map { col ->
            col.map { Tile(it) }.toTypedArray()
        }.toTypedArray()
    }
}
