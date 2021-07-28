package civ.core.map.map

import civ.core.map.tiles.Tile

class GameMap(private val tiles: Array<Array<Tile>>) {
    init {
        if (tiles.isEmpty()) {
            throw ExceptionInInitializerError("Map can't be empty")
        }
    }

    val size = Size(tiles.size, tiles[0].size)
    operator fun get(x: Int, y: Int) = tiles[x][y]
}
