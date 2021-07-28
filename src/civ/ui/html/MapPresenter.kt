package civ.ui.html

import civ.core.map.map.GameMap
import civ.core.map.tiles.Tile
import civ.core.map.tiles.Terrain

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.io.File

data class TileStyle(
    val color: String,
)

class HtmlMapPresenter {
    private val tileSize = Pair(10, 10)

    private fun getTileStyle(tile: Tile): TileStyle {
        return when (tile.terrain) {
            Terrain.PLAIN -> TileStyle("#3FBF3F")
            Terrain.HILL -> TileStyle("#4E6E4E")
            Terrain.MARSH -> TileStyle("#8F8C2D")
            Terrain.FOREST -> TileStyle("#3A873A")
            Terrain.DESERT -> TileStyle("#D6D028")
            Terrain.OCEAN -> TileStyle("#366EE7")
            Terrain.ICE -> TileStyle("#EBFEFC")
        }
    }

    fun print(map: GameMap) {
        val fileName = "map.html"

        val html = createHTML().html {
            head {
                title("Map")
                style {
                    unsafe {
                        raw(
                            """
                            .wrap  {
                                display: flex;
                                flex-wrap: wrap;
                                width: ${tileSize.first * map.size.width}px;
                            }
                            .tile {
                                width: ${tileSize.first}px;
                                height: ${tileSize.second}px;
                                display: flex;
                            }
                        """.trimIndent()
                        )
                    }
                }
            }
            body {
                div {
                    classes= setOf("wrap")

                    for (y in 0 until map.size.height) {
                        for (x in 0 until map.size.width) {
                            val style = getTileStyle(map[x, y])
                            div {
                                classes = setOf("tile")
                                attributes["style"] = "background-color: ${style.color}"
                            }
                        }
                    }
                }
            }
        }
        File(fileName).writeText(html)
    }
}
