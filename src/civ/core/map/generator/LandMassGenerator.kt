package civ.core.map.generator

import civ.core.map.map.Config
import civ.core.map.map.LandMass
import civ.core.map.map.Size
import civ.core.map.tiles.LandForm
import kotlin.random.Random

data class Position(
    val x: Int,
    val y: Int,
)

enum class Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
}

class LandMassGenerator(seed: Long) {
    private val rand = Random(seed)
    private lateinit var mapSize: Size

    fun generate(config: Config): Array<Array<LandForm>> {
        mapSize = config.size

        val landWaterRatio = when (config.landMass) {
            LandMass.SMALL -> 1 / 3f     // 1 to 2
            LandMass.NORMAL -> 1 / 2f    // 1 to 1
            LandMass.LARGE -> 2 / 3f     // 2 to 1
        }
        val land = Array(config.size.width) {
            Array(config.size.height) { LandForm.WATER }
        }

        val requiredSpawnedLand = (config.size.width * config.size.height * landWaterRatio).toInt()
        var totalSpawnedLand = 0

        while (totalSpawnedLand < requiredSpawnedLand) {
            totalSpawnedLand += spawnLand(land)
        }

        return land
    }

    private fun spawnLand(land: Array<Array<LandForm>>): Int {
        var pos = Position(rand.nextInt(0, mapSize.width), rand.nextInt(0, mapSize.height))
        val length = rand.nextInt(0, maxOf(mapSize.width, mapSize.height))

        var spawnedLands = 0
        val setLand = { x: Int, y: Int ->
            if (land[x][y] == LandForm.WATER) {
                spawnedLands++
            }
            land[x][y] = LandForm.LAND
        }

        repeat(length) {
            setLand(pos.x, pos.y)

            if (!thereIsSpaceOnEast(pos)) {
                return spawnedLands
            }
            setLand(pos.x + 1, pos.y)

            if (!thereIsSpaceOnSouth(pos)) {
                return spawnedLands
            }
            setLand(pos.x, pos.y + 1)

            pos = when (chooseAvailableDirection(pos)) {
                Direction.NORTH -> Position(pos.x, pos.y - 1)
                Direction.SOUTH -> Position(pos.x, pos.y + 1)
                Direction.EAST -> Position(pos.x + 1, pos.y)
                Direction.WEST -> Position(pos.x - 1, pos.y)
            }
        }
        return spawnedLands
    }

    private fun thereIsSpaceOnEast(pos: Position) = pos.x + 1 < mapSize.width
    private fun thereIsSpaceOnWest(pos: Position) = pos.x - 1 > 0
    private fun thereIsSpaceOnNorth(pos: Position) = pos.y - 1 > 0
    private fun thereIsSpaceOnSouth(pos: Position) = pos.y + 1 < mapSize.height

    private fun chooseAvailableDirection(pos: Position): Direction {
        val directions = mutableListOf<Direction>()
        if (thereIsSpaceOnEast(pos)) {
            directions.add(Direction.EAST)
        }
        if (thereIsSpaceOnWest(pos)) {
            directions.add(Direction.WEST)
        }
        if (thereIsSpaceOnNorth(pos)) {
            directions.add(Direction.NORTH)
        }
        if (thereIsSpaceOnSouth(pos)) {
            directions.add(Direction.SOUTH)
        }

        return directions[rand.nextInt(directions.size)]
    }
}
